package com.doran.redis.record.mapper;

import org.mapstruct.Mapper;

import com.doran.raw_voice.dto.res.RecordCheckDto;
import com.doran.redis.record.key.Record;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    Record toRecord(String id, Boolean maleAble, Boolean femaleAble);
    RecordCheckDto toCheckDto(Record record);
}
