package com.datastax.demo.stargate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.demo.stargate.chevrons.ChevronPrimaryKey;
import com.datastax.demo.stargate.chevrons.ChevronRepository;

@SpringBootTest
public class Ex2_UseSpringDataChevronTest {
    
    @Autowired
    private ChevronRepository chevronRepo;
    
    @Test
    public void listChevronsTest() {
        System.out.println("Here are the chevrons:");
        chevronRepo.findAll().stream().forEachOrdered(chevron -> {
            System.out.println("+"
                    + " code=" + chevron.getKey().getCode()
                    + ", name='" + chevron.getName() + "'");
        });
        System.out.println("\u001B[0m [OK] \u001B[32m - Test #2.1 Successful - you ROCK !\n\n");
    }
    
    @Test
    public void retrieveAChevronByPrimaryKey() {
        System.out.println("Earth !");
        chevronRepo.findById(new ChevronPrimaryKey("Milky Way",1 ))
                   .ifPresent(chevron -> System.out.println(chevron.getName()));
        System.out.println("\u001B[0m [OK] \u001B[32m - Test #2.2 Successful - you ROCK !\n\n");
    }
    
    @Test
    public void listChevronsMilkyWayTest() {
        System.out.println("Here are chevrons of the Milky Way");
        chevronRepo.findByKeyArea("Milky Way")
                   .stream()
                   .forEachOrdered(chevron -> {
            System.out.println("+"
                    + " code=" + chevron.getKey().getCode()
                    + ", name='" + chevron.getName() + "'");
        });
        System.out.println("\u001B[0m [OK] \u001B[32m - Test #2.3 Successful - you ROCK !\n\n");
    }

}
