package com.doran.page.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageFindDto {
    private int bookId;
    private String gender;
    //FEMALE, MALE
}
