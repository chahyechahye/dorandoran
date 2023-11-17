package com.doran.utils.rabbitmq.mapper;

import org.mapstruct.Mapper;

import com.doran.utils.rabbitmq.dto.res.WaitResDto;

@Mapper(componentModel = "spring")
public interface RabbitMapper {
    default WaitResDto toWaitResDto(Integer count, Integer time) {
        int hour = time / 60;
        int min = time % 60;

        String sb = "대기열 : "
            + count
            + "번 입니다. "
            + hour
            + "시간 "
            + min
            + "분 소요 예정입니다.";

        return WaitResDto.builder()
            .count(count)
            .time(sb)
            .build();
    }
}
