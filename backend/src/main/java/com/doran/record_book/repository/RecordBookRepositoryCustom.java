package com.doran.record_book.repository;

import java.util.List;
import java.util.Optional;

import com.doran.record_book.dto.res.ScriptDto;
import com.doran.record_book.entity.RecordBook;

public interface RecordBookRepositoryCustom {

    List<Long> findToTalPage(List<String> bookName);

    List<String> findBookName();

    List<ScriptDto> findScript(String bookName);

    Optional<RecordBook> findRecordBookByScript(String script, int scriptNum);

    void updateRecordBook();
}
