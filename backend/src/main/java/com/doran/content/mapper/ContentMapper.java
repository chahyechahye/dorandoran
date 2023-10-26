package com.doran.content.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.content.entity.Content;
import com.doran.page.entity.Page;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    @Mapping(source = "page.id", target = "id", ignore = true)
    Content toContent(Page page, String script);
}
