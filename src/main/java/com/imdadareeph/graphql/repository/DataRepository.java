package com.imdadareeph.graphql.repository;

import com.imdadareeph.graphql.entity.Data;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<Data, Integer> {

    Data findByEmail(String email);

}
