package com.doran.book;

import java.util.List;

import org.mapstruct.Mapper;

import com.doran.book.dto.req.BookInsertDto;
import com.doran.book.dto.res.BookResDto;
import com.doran.book.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookInsertToBook(BookInsertDto bookInsertDto, String imgUrl);
    BookResDto bookToResDto(Book book);
    List<BookResDto> toDtoList(List<Book> bookList);
}
