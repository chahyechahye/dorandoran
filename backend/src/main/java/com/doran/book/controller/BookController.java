package com.doran.book.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.book.dto.req.BookInsertDto;
import com.doran.book.service.BookService;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    // 동화 등록
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> insertBook(BookInsertDto bookInsertDto) throws IOException {
        log.info("insertBook 컨트롤러 호출");
        bookService.insertBook(bookInsertDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }

    //동화 전체 조회
    // 모든 동화 책, 제목, 표지, 저자
    // 반환되는 책 수
    @GetMapping("")
    ResponseEntity<?> getBookList() {
        log.info("getBookList 컨트롤러 호출");
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, bookService.getBookList());
    }
}
