package com.doran.book.controller;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    // 동화 등록,
    @PostMapping("")
    //@PreAuthorize("has")
    ResponseEntity<?> insertBook(BookInsertDto bookInsertDto) {
        int userId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());

        return null;
    }

    //동화 조회
    @GetMapping("")
    ResponseEntity<?> getBook() {
        return null;
    }
}
