package com.datastax.demo.stargate.destinations;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository 
    extends CassandraRepository<Destination, DestinationPrimaryKey> {
    
    /**
     * By Convention Spring Data Cassandra will do
     * SELECT * FROM destinations WHERE galaxy=''
     */
    List<Destination> findByKeyGalaxy(String area);
    
    
}
