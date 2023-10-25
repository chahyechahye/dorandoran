package com.doran.page.dto.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PageInsertDto {
    private MultipartFile multipartFile;
    private int idx;
}
