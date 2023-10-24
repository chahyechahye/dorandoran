package com.doran.book.dto.res;

import java.util.List;

import com.doran.book.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookListDto {
    private int size;
    private List<BookResDto> bookResDtoList;
}
