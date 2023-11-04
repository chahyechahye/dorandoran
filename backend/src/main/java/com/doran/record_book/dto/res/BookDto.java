package com.doran.record_book.dto.res;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {
    private String title;

    private List<ScriptDto> scriptList;
}
