package com.doran.redis.record.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doran.redis.invite.key.Invite;
import com.doran.redis.record.key.Record;

@Repository
public interface RecordRepository  extends CrudRepository<Record, String> {
}
