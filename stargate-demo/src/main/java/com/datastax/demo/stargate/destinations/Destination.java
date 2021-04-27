package com.datastax.demo.stargate.destinations;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "destinations")
public class Destination {
    
    @PrimaryKey
    private DestinationPrimaryKey key;
    
    private int chevron1;
    
    private int chevron2;
    
    private int chevron3;
    
    private int chevron4;
    
    private int chevron5;
    
    private int chevron6;
    
    private String description;
    
}
