package com.doran.redis.tel.mapper;

import org.mapstruct.Mapper;

import com.doran.redis.tel.key.Tel;

@Mapper(componentModel = "spring")
public interface TelMapper {
    Tel toTel(String userId, String tel);
}
