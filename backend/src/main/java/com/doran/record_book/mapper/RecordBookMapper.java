package com.doran.record_book.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.doran.record_book.dto.res.BookDto;
import com.doran.record_book.dto.res.RecordBookResDto;
import com.doran.record_book.dto.res.ScriptDto;

@Mapper(componentModel = "spring")
public interface RecordBookMapper {

    BookDto toBookDto(String title, List<ScriptDto> scriptList);

    RecordBookResDto toResDto(List<Long> totalScriptList, List<BookDto> bookList);
}
