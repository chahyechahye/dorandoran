package com.doran.page.mapper;

import com.doran.content.dto.res.ContentResDto;
import com.doran.page.dto.res.PageDetailDto;
import com.doran.page.dto.res.PageResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.book.entity.Book;
import com.doran.page.dto.req.PageInsertDto;
import com.doran.page.entity.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PageMapper {
    @Mapping(source = "book", target = "book")
    @Mapping(source = "book.id", target = "id", ignore = true)
    @Mapping(source = "imgUrl", target = "imgUrl")
    Page pageInsertToPage(Book book, String imgUrl, int idx);

    @Mapping(source = "id", target = "pageId")
    PageResDto pageToResDto(Page page);

    List<PageResDto> toDtoList(List<Page> pageList);

    @Mapping(source = "page.id", target = "pageId")
    PageDetailDto toDetailDto(Page page, ContentResDto contentResDto);


    default List<PageDetailDto> toDetailDtoList(List<Page> pageList, List<ContentResDto> contentResDto) {
        List<PageDetailDto> list = new ArrayList<PageDetailDto>( pageList.size() );

        for ( int i = 0; i < pageList.size() ; i ++ ) {
            list.add( toDetailDto( pageList.get(i), contentResDto.get(i) ));
        }

        return list;
    }

}
