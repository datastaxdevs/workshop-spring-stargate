package com.datastax.demo.stargate.chevrons;

import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "chevrons")
public class Chevron {
    private ChevronPrimaryKey key;
    private String name;
    private String picture;
}

