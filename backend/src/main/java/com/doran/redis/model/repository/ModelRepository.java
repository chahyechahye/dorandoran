package com.doran.redis.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doran.redis.model.key.Model;

@Repository
public interface ModelRepository extends CrudRepository<Model, String> {
}
