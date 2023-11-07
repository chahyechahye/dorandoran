package com.doran.raw_voice.dto.req;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompleteInsertDto {
    private Genders genders;
}
