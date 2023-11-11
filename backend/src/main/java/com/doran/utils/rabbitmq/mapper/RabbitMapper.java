package com.doran.utils.rabbitmq.mapper;

import org.mapstruct.Mapper;

import com.doran.utils.rabbitmq.dto.res.WaitResDto;

@Mapper(componentModel = "spring")
public interface RabbitMapper {
    WaitResDto toWaitResDto(Integer count, Integer time);
}
