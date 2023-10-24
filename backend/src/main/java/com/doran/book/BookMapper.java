package com.doran.book;

import org.mapstruct.Mapper;

import com.doran.book.dto.req.BookInsertDto;
import com.doran.book.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookInsertToBook(BookInsertDto bookInsertDto, String imgUrl);
}
