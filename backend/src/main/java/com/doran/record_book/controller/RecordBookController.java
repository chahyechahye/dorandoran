package com.doran.record_book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.record_book.dto.res.RecordBookResDto;
import com.doran.record_book.service.RecordBookService;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/record")
public class RecordBookController {

    private final RecordBookService recordBookService;

    //스크립트 전체 조회
    @GetMapping()
    public ResponseEntity findScript() {
        long start = System.currentTimeMillis();

        RecordBookResDto bookTitleList = recordBookService.findBookTitleList();
        
        log.info("걸린 시간(ms) : {}", System.currentTimeMillis() - start);

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, bookTitleList);
    }
}
