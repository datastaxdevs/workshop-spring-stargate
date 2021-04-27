package com.datastax.demo.stargate.destinations;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@PrimaryKeyClass
@AllArgsConstructor
@NoArgsConstructor
public class DestinationPrimaryKey {
    
    @PrimaryKeyColumn(name = "galaxy", 
            type = PrimaryKeyType.PARTITIONED, 
            ordinal = 0)
    private String galaxy;

    @PrimaryKeyColumn(name = "name", 
            type = PrimaryKeyType.CLUSTERED, 
            ordinal = 1, ordering = Ordering.ASCENDING)
    private String name;

}
