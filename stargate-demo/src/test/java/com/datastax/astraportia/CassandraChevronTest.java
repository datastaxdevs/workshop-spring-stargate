package com.datastax.astraportia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.astra.boot.autoconfigure.AstraConfiguration;
import com.datastax.astra.sdk.AstraClient;
import com.datastax.astra.sdk.AstraClient.AstraClientBuilder;
import com.datastax.oss.driver.api.core.CqlSession;

@SpringBootTest(classes = {AstraClient.class, AstraClientBuilder.class, AstraConfiguration.class})
public class CassandraChevronTest {
    
    @Autowired
    private CqlSession cqlSession;
    
    @Test
    public void useCqlSessionTest() {
        cqlSession.execute("select * from stargate.chevrons").all()
                  .stream()
                  .forEach(row -> { System.out.println(row.getString("name"));});
    }

}
