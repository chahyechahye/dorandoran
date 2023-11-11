package com.doran.book.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookResDto {
    private int bookId;
    private String title;
    private String characterUrl;
    private String author;
    private String publisher;
    private long totalPageCnt;
    private String imgUrl;
}
