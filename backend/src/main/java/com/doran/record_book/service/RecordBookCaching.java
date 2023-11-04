package com.doran.record_book.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.doran.record_book.dto.res.ScriptDto;
import com.doran.record_book.repository.RecordBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordBookCaching {
    private final RecordBookRepository recordBookRepository;

    @Cacheable(value = "record_script", key = "#bookName")
    public List<ScriptDto> findScript(String bookName) {

        return recordBookRepository.findScript(bookName);
    }
}
