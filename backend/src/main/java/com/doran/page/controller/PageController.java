package com.doran.page.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doran.book.entity.Book;
import com.doran.book.service.BookService;
import com.doran.page.dto.req.PageInsertDto;
import com.doran.page.service.PageService;
import com.doran.response.CommonResponseEntity;
import com.doran.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/page")
public class PageController {

    private final PageService pageService;

    // 동화책의 페이지 등록
    @PostMapping("/{book_id}")
    ResponseEntity<?> insertPage(@PathVariable(value = "book_id") int bookId, PageInsertDto pageInsertDto) {
        log.info("insertPage 컨트롤러 호출");
        pageService.insertPage(bookId, pageInsertDto);

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }

    // 페이지 조회
}
