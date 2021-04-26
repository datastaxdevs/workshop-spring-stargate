package com.datastax.demo.stargate.chevrons;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChevronRepository 
    extends CassandraRepository<ChevronPrimaryKey, Chevron> {}
