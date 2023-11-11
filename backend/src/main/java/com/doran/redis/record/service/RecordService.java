package com.doran.redis.record.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doran.redis.record.key.Record;
import com.doran.redis.record.mapper.RecordMapper;
import com.doran.redis.record.repository.RecordRepository;
import com.doran.utils.common.Genders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordService {
    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper;

    public Record save(Record record) {
        recordRepository.save(record);
        return record;
    }

    public Optional<Record> findById(String id) {
        return recordRepository.findById(id);
    }

    public void delete(Record record) {
        recordRepository.delete(record);
    }

    public void update(int userId, Record record, Genders genders) {
        Boolean maleAble = record.getMaleAble();
        Boolean femaleAble = record.getFemaleAble();

        if (genders.equals(Genders.MALE))
            maleAble = true;
        else
            femaleAble = true;

        delete(record);
        save(recordMapper.toRecord(String.valueOf(userId), maleAble, femaleAble));
    }
}
