package com.doran.book.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.book.dto.req.BookInsertDto;
import com.doran.book.dto.res.BookResDto;
import com.doran.book.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookInsertToBook(BookInsertDto bookInsertDto, String imgUrl);
    @Mapping(source = "id", target = "bookId")
    BookResDto bookToResDto(Book book);
    List<BookResDto> toDtoList(List<Book> bookList);
}
