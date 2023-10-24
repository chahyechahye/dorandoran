package com.doran.book.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookResDto {
    private String title;
    private String imgUrl;
    private String author;
    private String publisher;
}
