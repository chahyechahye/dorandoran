package com.doran.book.dto.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookInsertDto {
    private String title;
    private MultipartFile multipartFile;
    private String author;
    private String publisher;
    private MultipartFile multipartFile2;
}
