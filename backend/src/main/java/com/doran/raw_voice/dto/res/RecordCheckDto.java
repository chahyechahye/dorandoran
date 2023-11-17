package com.doran.raw_voice.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordCheckDto {
    private Boolean maleAble;
    private Boolean femaleAble;
}
