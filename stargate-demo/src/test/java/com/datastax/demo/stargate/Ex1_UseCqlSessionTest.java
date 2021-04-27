package com.datastax.demo.stargate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.oss.driver.api.core.CqlSession;

@SpringBootTest
public class Ex1_UseCqlSessionTest {
    
    @Autowired
    private CqlSession cqlSession;
    
    @Test
    public void listChevronsTest() {
        System.out.println("Here are the chevrons:");
        cqlSession.execute("select * from stargate.chevrons")
                  .all().stream().forEach(row -> {
           System.out.println("+"
                   + " code=" + row.getInt("code") 
                   + ", name='" + row.getString("name") + "'");
        });
        
        System.out.println("\u001B[0m [OK] \u001B[32m - Test Successfully you ROCK !\n\n");
    }

}
