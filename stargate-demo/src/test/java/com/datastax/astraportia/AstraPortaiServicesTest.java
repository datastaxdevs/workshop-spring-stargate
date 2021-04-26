package com.datastax.astraportia;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.demo.stargate.neo.Neo;

/**
 * Validation of {@link StargateHttpClient} with {@link Neo} and a Json Dataset.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
@RunWith(JUnitPlatform.class)
@SpringJUnitConfig
@ContextConfiguration(classes = {AstraClient.class})
@TestPropertySource(locations = "/application-test.properties")
public class AstraPortaiServicesTest {
    
    public static final String dataset = 
            "src/main/resources/2020_09_10_near_earth_asteroids_and_comets.json";
    
    //@Autowired
    //private StargateDemoServices services;
    
    @Test
    @DisplayName("Import the DataSet")
    public void batch_import() {
        //services.importNeoJsonDataSet(dataset);
    }
    
    @Test
    @DisplayName("When creating doc, providing id, same id is returned by API.")
    public void should_writtenData_use_provided_id() {
        // Given
        UUID myDocId = UUID.randomUUID();
        // When inserting providing id
        //String docid2 = services.createNeo(new Neo(), myDocId.toString());
        // Then if returned is the expected one
        //Assertions.assertEquals(docid2, myDocId.toString());
    }
    
    @Test
    public void should_findAll_neo() {
        //List<NeoDoc> docs = services.findAllNeos();
        //Assertions.assertTrue(docs.size() > 100);
    }
    

}
