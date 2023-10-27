package com.doran.redis.balcklist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doran.redis.balcklist.key.BlackList;

@Repository
public interface BlackListRepository extends CrudRepository<BlackList, String> {
}
