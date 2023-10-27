package com.doran.redis.balcklist.Mapper;

import org.mapstruct.Mapper;

import com.doran.redis.balcklist.key.BlackList;

@Mapper(componentModel = "spring")
public interface BlackListMapper {
    BlackList toBlackList(String value);
}
