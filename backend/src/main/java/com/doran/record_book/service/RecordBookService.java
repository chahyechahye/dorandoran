package com.doran.record_book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.record_book.dto.res.BookDto;
import com.doran.record_book.dto.res.RecordBookResDto;
import com.doran.record_book.dto.res.ScriptDto;
import com.doran.record_book.mapper.RecordBookMapper;
import com.doran.record_book.repository.RecordBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordBookService {
    private final RecordBookRepository recordBookRepository;
    private final RecordBookCaching recordBookCaching;
    private final RecordBookMapper recordBookMapper;

    //책 리스트 조회
    public RecordBookResDto findBookTitleList() {
        List<String> bookName = recordBookRepository.findBookName();
        List<Long> totalScript = findTotalScript(bookName);

        List<BookDto> bookList = bookName.stream()
            .map(name -> {
                log.info("캐싱된 데이터 있으면 쿼리 발생하지 않음");
                List<ScriptDto> script = recordBookCaching.findScript(name);
                return recordBookMapper.toBookDto(name, script);
            })
            .toList();

        return recordBookMapper.toResDto(totalScript, bookList);
    }

    //페이지 수 조회
    public List<Long> findTotalScript(List<String> bookName) {
        return recordBookRepository.findToTalPage(bookName);
    }

}
