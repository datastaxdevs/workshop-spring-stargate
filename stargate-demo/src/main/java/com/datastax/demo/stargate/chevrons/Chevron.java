package com.datastax.demo.stargate.chevrons;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "chevrons")
public class Chevron {
    
    @PrimaryKey
    private ChevronPrimaryKey key;
    
    @Column("name")
    @CassandraType(type = Name.TEXT)
    private String name;
    
    @Column("picture")
    @CassandraType(type = Name.TEXT)
    private String picture;
}

