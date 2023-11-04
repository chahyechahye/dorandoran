package com.doran.redis.tel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doran.redis.tel.key.Tel;

@Repository
public interface TelRepository extends CrudRepository<Tel, String> {
}
