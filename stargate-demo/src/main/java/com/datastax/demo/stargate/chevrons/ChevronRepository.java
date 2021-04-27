package com.datastax.demo.stargate.chevrons;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChevronRepository extends CassandraRepository<Chevron, ChevronPrimaryKey> {
    
    
    /**
     * By Convention Spring Data Cassandra will do
     * SELECT * FROM chevrons WHERE area=''
     */
    List<Chevron> findByKeyArea(String area);
    
    
}
