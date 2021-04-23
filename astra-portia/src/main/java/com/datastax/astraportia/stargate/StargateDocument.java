package com.datastax.astraportia.stargate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Working document with StargateApi.
 *
 * @author Cedrick LUNVEN (@clunven)
 *
 * @param <T>
 *      current object
 */
@JsonIgnoreProperties
public class StargateDocument<T> implements Serializable {
    
    /** Serial. */
    private static final long serialVersionUID = -9031206707641391885L;
    
    /** Provide the collection Name . */
    @JsonIgnore
    protected String collectionName;
    
    @JsonProperty("documentId")
    protected String documentId = null;
    
    @JsonProperty("data")
    protected T data;
    
    /**
     * Default constructor
     */
    public StargateDocument() {}
    
    public StargateDocument(T val) {
        this.data = val;
    }
    
    public StargateDocument(T val, String docid) {
        this(val);
        this.documentId = docid;
    }
    
    @SuppressWarnings("unchecked")
    protected String getGenericName() {
        return ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName();
    }
    
    /**
     * Getter accessor for attribute 'collectionName'.
     *
     * @return
     *       current value of 'collectionName'
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * Setter accessor for attribute 'collectionName'.
     * @param collectionName
     * 		new value for 'collectionName '
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * Getter accessor for attribute 'documentId'.
     *
     * @return
     *       current value of 'documentId'
     */
    public Optional<String> getDocumentId() {
        return Optional.ofNullable(documentId);
    }

    /**
     * Setter accessor for attribute 'documentId'.
     * @param documentId
     * 		new value for 'documentId '
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    /**
     * Getter accessor for attribute 'data'.
     *
     * @return
     *       current value of 'data'
     */
    public T getData() {
        return data;
    }

    /**
     * Setter accessor for attribute 'data'.
     * @param data
     * 		new value for 'data '
     */
    public void setData(T data) {
        this.data = data;
    }
}
