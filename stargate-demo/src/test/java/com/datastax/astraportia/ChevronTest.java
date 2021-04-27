package com.datastax.astraportia;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.datastax.astra.boot.autoconfigure.AstraClientProperties;
import com.datastax.astra.boot.autoconfigure.AstraConfiguration;
import com.datastax.astra.sdk.AstraClient;
import com.datastax.astra.sdk.AstraClient.AstraClientBuilder;

@RunWith(JUnitPlatform.class)
@SpringJUnitConfig
@ContextConfiguration
@TestPropertySource(locations = "/application-test.properties")
public class ChevronTest {

    @Value("${astra.applicationToken}")
    private String token;
    
    @Test
    public void listAvailableDatabases() {
        AstraClient.builder()
                   .appToken("AstraCS:QYWtywhlasmujIrTCRsfGcge:0fae655b765930c517a0215e876fef2aa2728c1ef0fd3f91bdda6fc69f54c128")
                   .build()
                   .apiDevops()
                   .findAllDatabasesNonTerminated()
                   .forEach(db -> {
          System.out.println("Database '" + db.getInfo().getName() + "'");
          System.out.println("+ id=" + db.getId());
          System.out.println("+ region=" + db.getInfo().getRegion());
          System.out.println("+ keyspace=" + db.getInfo().getKeyspace());
          System.out.println("");
        });
    }
    
    
    
    
    
}
