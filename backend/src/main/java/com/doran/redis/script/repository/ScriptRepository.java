package com.doran.redis.script.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doran.redis.script.key.Script;

@Repository
public interface ScriptRepository extends CrudRepository<Script, String> {
}
