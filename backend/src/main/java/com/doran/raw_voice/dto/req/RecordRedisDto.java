package com.doran.raw_voice.dto.req;

import com.doran.utils.common.Genders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordRedisDto {
    private int userId;
    private Genders gender;
}
