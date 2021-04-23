package com.datastax.astraportia.stargate;

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 * Client for the Stargate Document REST API.
 * 
 * Disclaimer: With the limited amount of time and current state of the api 
 * (error management, http codes) quality is average. Group operations are
 * working but not scalable, we would consider asyncCalls.
 * 
 * As of now, the client works with an unique namespace (defined in at startup).
 *
 * Authentication
 * - The authToken is generated if needed and revew when configured timeout is reached.
 * 
 * C.R.U.D operations :
 * - CREATE : create if not exist (PUT), upsert (PATCH)
 * - READ   : exists(GET), findById (GET), findAllDocsIds(), findAllDocs()
 * - UPDATE : upsert (PATCH)
 * - DELETE : deleteById(), clear()
 * 
 * BASIC File Imports:
 * - Import JSON
 * - Import CSV
 *
 * @author Cedrick LUNVEN (@clunven)
 * @since  Stargate Hackathon (2020-09-11)
 */
@Component
public class StargateHttpClient {

    /** Logger for the class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StargateHttpClient.class);
   
    /** Header for authToken. */
    public static final String HEADER_CASSANDRA = "X-Cassandra-Token";
    
    /** Retention period for authToken in seconds, default is 5 MIN */
    public static final int DEFAULT_TTL_SECONDS = 300;
    
    /** Set a timeout for Http requests. */
    public static final Duration REQUEST_TIMOUT = Duration.ofMinutes(1);
    
    /** Url as been chosen againt regionId, dbId as easier to copy from Astra UI. */
    @Value("${stargate.url}")
    private String url;
    
    /** Username in Astra. */
    @Value("${stargate.username}")
    private String username;
    
    /** Password in Astra. */
    @Value("${stargate.password}")
    private String password;
    
    /** Existing keyspace in Astra, current API doest not allow keyspace create (devops API does) */
    @Value("${stargate.namespace}")
    private String namespace;
    
    /** Handling authToken internally with as stateful strategy.*/
    private String authToken             = null;
    private int    authTokenTTLSeconds   = DEFAULT_TTL_SECONDS;
    private long   authTokenCreationDate = 0;
    
    /** Core Java 11 Http Client (limiting dependencies to third-party and ensure portability) **/
    private HttpClient httpClient;
    
    /** Object <=> Json marshaller as a Jackson Mapper. */
    private ObjectMapper objectMapper;
    
    /** Default Constructor, notice as mapper and HttpClient are initialized. */
    public StargateHttpClient() {
        initJacksonObjectMapper();
        initHttpClient();
    }
    
    /** Constructors with params, Cound be immutable and provide a Builder (but for 6 params.) */
    public StargateHttpClient(String url, String username, String password, 
                              String namespace, int authTokenTTL) {
        this();
        this.url = url;
        this.username = username;
        this.password = password;
        this.namespace = namespace;
        this.authTokenTTLSeconds = authTokenTTL;
    }
    
    /** Generate an Authentication Token for the queries. */
    public String getAuthToken() {
        if ((System.currentTimeMillis() - authTokenCreationDate) > (authTokenTTLSeconds * 1000)) {
            LOGGER.info("Renewing AuthToken...");
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uriAuthenticate()).timeout(REQUEST_TIMOUT)
                        .header("Content-Type", "application/json")
                        .POST(BodyPublishers.ofString(bodyAuthenticate())).build();
                HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
                if (HttpStatus.OK.value() == response.statusCode()) {
                   authToken = (String) objectMapper.readValue(response.body(), Map.class).get("authToken");
                   authTokenCreationDate = System.currentTimeMillis();
                   LOGGER.info("[OK] - Success. Token will live for {} second(s).", authTokenTTLSeconds);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("An error occured", e);
            }
        }
        return authToken;
    }
    
    /**
     * Using current resource GET to evaluate if a document exists.
     *
     * - 200 means the document exists
     * - otherwise it does not. As of now, the API return 204 if not found (it should be 404)
     */
    public boolean exists(String collectionName, String documentId) {
        Objects.requireNonNull(collectionName);
        Objects.requireNonNull(documentId);
        try {
            HttpRequest req = httpRequest()
                    .uri(uriFindById(documentId, collectionName))
                    .GET().build();
            return HttpStatus.OK.value() == httpClient.send(req, BodyHandlers.ofString()).statusCode();
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occured", e);
        }
    }
    
    /**
     * Using PUT and POST to create document (not PATCH here)
     * - PUT if a documentId is communicated
     * - POST if not
     * 
     * One return 200 and the other 201, explain the OR statement.
     * 
     * @param <D>
     *      serializable object to convert as JSON  
     * @param authToken
     *      authentication token required in headers
     * @param doc
     *      document to be serialized (using Jackson)
     * @param docId
     *      optional unique identifier for the document
     * @param collectionName
     *      collection name
     * @return
     *      return document Id (the one provided eventually)
     */
    public <D extends Serializable> String create(StargateDocument<D> doc) {
        Objects.requireNonNull(doc);
        Objects.requireNonNull(doc.getData());
        Objects.requireNonNull(doc.getCollectionName());
        try {
            // Creating Req
            Builder reqBuilder = httpRequest().uri(uriCreateNewDocument(doc));
            String reqBody = objectMapper.writeValueAsString(doc.getData());
            
            // PUT to create a new enforcing Id,POST to create a new with no id 
            if (doc.getDocumentId().isEmpty()) {
                reqBuilder.POST(BodyPublishers.ofString(reqBody));
            } else {
                // An Id has been provided, we want to raise an error if already exist
                reqBuilder.PUT(BodyPublishers.ofString(reqBody));
            }
            
            // Call
            HttpResponse<String> response = httpClient.send(reqBuilder.build(), BodyHandlers.ofString());
            if (null !=response && (
                    response.statusCode() == HttpStatus.CREATED.value() || 
                    response.statusCode() == HttpStatus.OK.value())) {
                return (String) objectMapper.readValue(response.body(), Map.class).get("documentId");
            } else {
                throw new IllegalArgumentException(response.body());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occured", e);
        }
    }
    
    /**
     * Using the GET resource to return object. (object mapping leveraging Jackson).
     *
     * @param <B>
     *          current DTO
     * @param collectionName
     *          collection Name
     * @param docId
     *          target document id
     * @param clazz
     *          Dto class to allow dynamic mapping,
     * @return
     */
    public <B extends StargateDocument<?>> Optional<B> findById(String collectionName, String docId, Class<B> clazz) {
        Objects.requireNonNull(collectionName);
        Objects.requireNonNull(docId);
        Objects.requireNonNull(clazz);
        try {
            HttpRequest req = httpRequest().uri(uriFindById(docId, collectionName)).GET().build();
            HttpResponse<String> response = httpClient.send(req, BodyHandlers.ofString());
            if (null !=response && response.statusCode() == HttpStatus.OK.value()) {
                return Optional.of(objectMapper.readValue(response.body(), clazz));
            } else if (HttpStatus.NO_CONTENT.value() == response.statusCode()) {
                return Optional.empty();
            } else {
                throw new IllegalArgumentException("An error occured: " + response.body());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occured", e);
        }
    }
    
    /**
     * Leveraging resources DELETE to remove a document, it must exists.
     *
     * @param collectionName
     *          collectionName
     * @param docId
     *          documentId
     */
    public void delete(String collectionName, String docId) {
        if (!exists(collectionName, docId)) {
            throw new IllegalArgumentException("Invalid collectionName/documentId, this object does not exist"); 
        }
        try {
            HttpRequest req = httpRequest().uri(uriFindById(docId, collectionName)).DELETE().build();
            httpClient.send(req, BodyHandlers.ofString());
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occured", e);
        }
    }
    
    /**
     * Leveraging resources PATCH to implement UPSERT.
     * 
     * @param <D>
     *      cuurent DTO
     * @param doc
     *      wrapper for Stargate
     * @return
     *      document id (the one provided in doc)
     */
    public <D extends Serializable> String upsert(StargateDocument<D> doc) {
        Objects.requireNonNull(doc);
        if (doc.getDocumentId().isEmpty()) {
            throw new IllegalArgumentException("Cannot upsert if not documentId is provided.");
        }
        try {
            Builder reqBuilder = httpRequest()
                    .uri(uriCreateNewDocument(doc))
                    .method("PATCH", BodyPublishers.ofString(objectMapper.writeValueAsString(doc.getData())));
            HttpResponse<String> response = httpClient.send(reqBuilder.build(), BodyHandlers.ofString());
            // Patch always return 200
            if (null !=response && HttpStatus.OK.value() == response.statusCode()) {
                return (String) objectMapper.readValue(response.body(), Map.class).get("documentId");
            } else {
                throw new IllegalArgumentException(response.body());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occured", e);
        }
    }
    
    /**
     * Leveraring the $exist filter operator to get all documents (with an always present property like PK)
     *
     * @param collectionName
     *          collection name for the document
     * @param propertyName
     *          property name of the document
     * @return
     *        set of ids
     */
    public Set<String> findAllDocumentIds(String collectionName, String propertyName) {
        return findIds(uriFindAllIds(collectionName, propertyName));
    }
    
    /**
     * Leveraring the $eq filter operator to get documents matching a criteria
     *
     * @param collectionName
     *          collection name for the document
     * @param propertyName
     *          property name of the document
     * @param propertyValue
     *         expected value      
     * @return
     *        set of ids
     */
    public Set<String> findDocumentsIdsFilterByPropertyValue(String collectionName, String propertyName, String propertyValue) {
        return findIds(uriFindByPropertyValue(collectionName, propertyName, propertyValue));
    }
    
    /**
     * Syntax sugar..
     */
    @SuppressWarnings("unchecked")
    private Set<String> findIds(URI uri) {
        Objects.requireNonNull(uri);
        try {
            HttpResponse<String> response = httpClient.send(httpRequest().uri(uri).GET().build(), BodyHandlers.ofString());
            if (null !=response && HttpStatus.OK.value() == response.statusCode()) {
                Map<String, Object> o = (Map<String, Object>) objectMapper.readValue(response.body(), Map.class).get("data");
                return o.keySet();
            } else {
                throw new IllegalArgumentException(response.body());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occured", e);
        }
    }
    
    /**
     * Build URL to GET all ids with 'exists' OP.
     * Note that the query need to be ENCODE and all '{' '}' are escaped %2F..
     */
    private URI uriFindAllIds(String colName, String key) {
        Objects.requireNonNull(colName);
        Objects.requireNonNull(key);
        return UriComponentsBuilder.fromUriString(url
                + "/v2/namespaces/" + namespace + "/collections/" + colName)
                .queryParam("where", "{\"" + key + "\": {\"$exists\": true}}")
                .build().encode().toUri();
    }
    
    /**
     * Build URL to GET all ids matching a criteria.
     * Note that the query need to be ENCODE and all '{' '}' are escaped %2F..
     */
    private URI uriFindByPropertyValue(String colName, String propName, String propValue) {
        // Building search Query
        return UriComponentsBuilder.fromUriString(url
                + "/v2/namespaces/" + namespace + "/collections/" + colName)
                .queryParam("where", "{\"" + propName + "\": {\"$eq\": \"" + propValue + "\"}}")
                .build().encode().toUri();
    }
    
    private URI uriAuthenticate() {
        return URI.create(url + "/v1/auth/");
    }
    
    private String bodyAuthenticate() {
        return "{ "
                + "\"username\": \""+ username +"\", "
                + "\"password\": \""+ password + "\"}";
    }
    
    private URI uriCreateNewDocument(StargateDocument<?> sDoc) {
        String uri = url + "/v2/namespaces/" + namespace + "/collections/" + sDoc.getCollectionName() + "/";
        if (sDoc.getDocumentId().isPresent()) {
            uri = uri + sDoc.getDocumentId().get();
        }
        return URI.create(uri);
    }
    
    private URI uriFindById(String docId, String collectionName) {
        return URI.create(url + "/v2/namespaces/" + namespace + "/collections/" + collectionName + "/" + docId);
    }
    
    private void initJacksonObjectMapper() {
        // Setup Jackson
           objectMapper = new ObjectMapper();
           objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
           objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
           objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
           objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
           objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
           objectMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
           objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
           LOGGER.info("Jackson serializer has been initialized");
    } 
       
    private void initHttpClient() {
        httpClient = HttpClient.newBuilder()
                   .version(Version.HTTP_2)
                   .followRedirects(Redirect.NORMAL)
                   .build();
        LOGGER.info("Http Client has been initialized");
    }
    
    private Builder httpRequest() {
        return HttpRequest.newBuilder()
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .header(HEADER_CASSANDRA, getAuthToken());
    }
    
    /**
     * Getter accessor for attribute 'objectMapper'.
     *
     * @return
     *       current value of 'objectMapper'
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    
    /**
     * Getter accessor for attribute 'keyspace'.
     *
     * @return
     *       current value of 'keyspace'
     */
    public String getNameSpace() {
        return namespace;
    }
}
