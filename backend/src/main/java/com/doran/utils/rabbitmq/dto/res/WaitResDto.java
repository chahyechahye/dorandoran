package com.doran.utils.rabbitmq.dto.res;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WaitResDto {
    private Integer count;
    private String time;
}
