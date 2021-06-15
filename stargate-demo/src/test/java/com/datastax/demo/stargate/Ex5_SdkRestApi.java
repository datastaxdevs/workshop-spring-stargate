package com.datastax.demo.stargate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.demo.stargate.chevrons.Chevron;
import com.datastax.stargate.sdk.rest.domain.RowMapper;
import com.datastax.stargate.sdk.rest.domain.SearchTableQuery;

@SpringBootTest
public class Ex5_SdkRestApi {

    @Autowired
    private AstraClient astraClient;
    
    @Test
    public void listChevrons() {
        astraClient.apiStargateData().keyspace("stargate").table("chevrons")
                   .search(SearchTableQuery.builder().build(), new ChevronRowMapper())
                   .getResults().stream()
                   .map(Chevron::getName)
                   .forEach(System.out::println);
    }
    
    private static class ChevronRowMapper implements RowMapper<Chevron> {
        @Override
        public Chevron map(com.datastax.stargate.sdk.rest.domain.Row row) {
            Chevron c = new Chevron();
            c.setName(row.getString("name"));
            //...
            return c;
        }
        
    }
    
    
}
