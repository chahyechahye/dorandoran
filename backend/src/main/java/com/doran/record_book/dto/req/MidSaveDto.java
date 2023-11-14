package com.doran.record_book.dto.req;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MidSaveDto {
    private Genders genders;
}
