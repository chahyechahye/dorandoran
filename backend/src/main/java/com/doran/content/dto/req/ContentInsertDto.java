package com.doran.content.dto.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContentInsertDto {
    private int idx;
    private String script;
    private MultipartFile multipartFile;
}
