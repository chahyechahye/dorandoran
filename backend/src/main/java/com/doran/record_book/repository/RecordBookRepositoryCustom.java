package com.doran.record_book.repository;

import java.util.List;

import com.doran.record_book.dto.res.ScriptDto;

public interface RecordBookRepositoryCustom {

    List<Long> findToTalPage(List<String> bookName);

    List<String> findBookName();

    List<ScriptDto> findScript(String bookName);
}
