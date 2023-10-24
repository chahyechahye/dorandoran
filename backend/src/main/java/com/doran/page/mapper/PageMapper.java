package com.doran.page.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.book.entity.Book;
import com.doran.page.dto.req.PageInsertDto;
import com.doran.page.entity.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {
    @Mapping(source = "book", target = "book")
    @Mapping(source = "imgUrl", target = "imgUrl")
    Page pageInsertToPage(Book book, String imgUrl, int idx);
}
