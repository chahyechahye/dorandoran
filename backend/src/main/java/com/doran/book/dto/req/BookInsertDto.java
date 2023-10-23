package com.doran.book.dto.req;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookInsertDto {
    private String title;
    private String imgUrl;
    private String author;
    private String publisher;
}
