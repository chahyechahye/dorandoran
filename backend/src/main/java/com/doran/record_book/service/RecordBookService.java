package com.doran.record_book.service;

import static com.doran.record_book.entity.QRecordBook.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.record_book.repository.RecordBookRepository;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordBookService {
    private final RecordBookRepository recordBookRepository;

    //책 리스트 조회
    public void findBookTitleList() {
        List<Tuple> toTalPage = recordBookRepository.findToTalPage();

        for (Tuple tuple : toTalPage) {
            tuple.get(recordBook.count());
        }
    }
}
