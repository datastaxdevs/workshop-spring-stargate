package com.datastax.astraportia;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class SnadboxTest {

    @Test
    public void mytestRandom() {
        int min = 0;
        int max = 20;
        for(int i=0;i<10;i++) {
            System.out.println(new Random().nextInt((max - min) + 1) + min);
        }
    }
    
    @Test
    public void formatTest() {
        System.out.println(String.format("%1$" + 3 + "s", "31").replace(' ', '0'));

        
    }
}
