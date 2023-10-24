package com.doran.book.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.book.dto.req.BookInsertDto;
import com.doran.book.service.BookService;
import com.doran.response.CommonResponseEntity;
import com.doran.response.SuccessCode;

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
    //@PreAuthorize("has") //권한 체킹
    ResponseEntity<?> insertBook(BookInsertDto bookInsertDto) throws IOException {
        log.info("insertBook 컨트롤러 호출");
        //int userId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        bookService.insertBook(bookInsertDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }

    //동화 조회
    @GetMapping("")
    ResponseEntity<?> getBook() {
        return null;
    }
}
