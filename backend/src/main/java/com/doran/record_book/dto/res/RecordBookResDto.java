package com.doran.record_book.dto.res;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordBookResDto {
    private List<Long> totalScriptList;

    private List<BookDto> bookList;

}
