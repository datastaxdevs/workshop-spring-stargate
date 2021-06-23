package com.datastax.demo.stargate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.stargate.sdk.doc.domain.SearchDocumentQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootTest
public class Ex6_SdkDocApi {


    @Autowired
    private AstraClient astraClient;
    
    @Test
    public void listDocument() {
        
        if (!astraClient.apiStargateDocument()
                .namespace("stargate")
                .exist()) {
            throw new IllegalArgumentException("Make sure you use 'stargate' as namespace");
        }
        
        if (!astraClient.apiStargateDocument()
                .namespace("stargate")
                .collection("sampledoc")
                .exist()) {
            throw new IllegalArgumentException("Make sure you use create a doc in `sampledoc1` step 11a : https://github.com/datastaxdevs/workshop-spring-stargate#11-using-stargate-document-api");
        }
        
        astraClient.apiStargateDocument()
                   .namespace("stargate")
                   .collection("sampledoc")
                   .search(SearchDocumentQuery.builder().build(), VideoBean.class)
                   .forEach(doc -> {
                       System.out.println("Document:");
                       System.out.println("+ id=" + doc.getDocumentId());
                       System.out.println("+ email= " + doc.getDocument().getEmail());
                       System.out.println("");
        });
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class VideoBean {
        private UUID videoid;
        private String email;
        private String upload;
        private String url;
        private List<Integer> frames;
        private Set<String> tags;
        private Map<String, Map<String, Integer>> formats;
    }
    
    
}
