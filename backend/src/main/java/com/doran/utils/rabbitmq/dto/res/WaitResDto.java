package com.doran.utils.rabbitmq.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WaitResDto {
    private Integer count;
    private Integer time;
}
