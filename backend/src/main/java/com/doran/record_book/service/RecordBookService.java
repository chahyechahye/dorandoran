package com.doran.record_book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.record_book.dto.res.BookDto;
import com.doran.record_book.dto.res.RecordBookResDto;
import com.doran.record_book.dto.res.ScriptDto;
import com.doran.record_book.repository.RecordBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordBookService {
    private final RecordBookRepository recordBookRepository;

    //책 리스트 조회
    public RecordBookResDto findBookTitleList() {
        RecordBookResDto recordBookResDto = new RecordBookResDto();
        List<String> bookName = recordBookRepository.findBookName();
        List<Long> totalScriptList = recordBookRepository.findToTalPage(bookName);

        recordBookResDto.setTotalScriptList(totalScriptList);
        List<BookDto> bookDtoList = new ArrayList<>();

        for (String s : bookName) {
            BookDto bookDto = new BookDto();
            List<ScriptDto> script = recordBookRepository.findScript(s);
            bookDto.setTitle(s);
            bookDto.setScriptList(script);
            bookDtoList.add(bookDto);
        }
        recordBookResDto.setBookList(bookDtoList);
        return recordBookResDto;
    }

}
