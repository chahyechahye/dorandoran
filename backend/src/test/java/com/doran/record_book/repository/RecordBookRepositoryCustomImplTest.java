package com.doran.record_book.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doran.record_book.entity.RecordBook;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RecordBookRepositoryCustomImplTest {
    @Autowired
    RecordBookRepository recordBookRepository;

    @Test
    public void 레코드북_테스트() {
        Optional<RecordBook> recordBookByScript = recordBookRepository.findRecordBookByScript(
            "옛날 옛적 한겨울에, 하늘에서 눈송이가 깃털처럼 내리고 있었어요. 그때 어느 왕비가 흑단 나무로 만든 창틀에 앉아 바느질을 하고 있었어요."
            , 1);

        recordBookByScript.ifPresent((recordBook -> {
            log.info(recordBook.getScript());
        }));
    }

}
