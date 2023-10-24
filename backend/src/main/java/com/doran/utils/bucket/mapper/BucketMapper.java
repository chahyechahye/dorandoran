package com.doran.utils.bucket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.book.dto.req.BookInsertDto;
import com.doran.utils.bucket.dto.InsertDto;

@Mapper(componentModel = "spring")
public interface BucketMapper {
    @Mapping(source = "title", target = "name")
    @Mapping(source = "multipartFile", target = "file")
    InsertDto bookInsertToBucket(BookInsertDto bookInsertDto);
}
