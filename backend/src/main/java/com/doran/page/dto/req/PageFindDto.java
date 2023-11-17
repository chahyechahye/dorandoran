package com.doran.page.dto.req;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageFindDto {
    private int bookId;
    private Genders gender;
    //FEMALE, MALE
}
