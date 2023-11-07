package com.doran.processed_voice.dto.req;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PVNewInsertDto {
    private int bookId;
    private Genders genders;
}
