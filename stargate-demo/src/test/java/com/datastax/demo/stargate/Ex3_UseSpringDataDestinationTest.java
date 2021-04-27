package com.datastax.demo.stargate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.demo.stargate.destinations.DestinationPrimaryKey;
import com.datastax.demo.stargate.destinations.DestinationRepository;

@SpringBootTest
public class Ex3_UseSpringDataDestinationTest {
    
    @Autowired
    private DestinationRepository destinationRepository;
    
    @Test
    public void findDestination() {
        System.out.println("Here are the chevrons:");
        destinationRepository
        .findById(new DestinationPrimaryKey("Milky Way", "Chulak"))
        .get();
        System.out.println("\u001B[0m [OK] \u001B[32m - Test #2.1 Successful - you ROCK !\n\n");
    }

}
