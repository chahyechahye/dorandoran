package com.doran.page.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doran.content.service.ContentService;
import com.doran.page.dto.req.PageFindDto;
import com.doran.page.dto.req.PageInsertDto;
import com.doran.page.dto.res.PageDetailDto;
import com.doran.page.dto.res.PageListDto;
import com.doran.page.service.PageService;
import com.doran.utils.auth.Auth;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/page")
public class PageController {

    private final PageService pageService;
    private final ContentService contentService;

    // 동화책의 페이지 등록
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{book_id}")
    ResponseEntity<?> insertPage(@PathVariable(value = "book_id") int bookId, PageInsertDto pageInsertDto) {
        log.info("insertPage 컨트롤러 호출");
        pageService.insertPage(bookId, pageInsertDto);

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }

    // 페이지 조회
    @GetMapping("/{book_id}")
    ResponseEntity<?> getPageList(@PathVariable(value = "book_id") int bookId) {
        log.info("getPageList 컨트롤러 호출");

        PageListDto result = pageService.findPageByBookIdWithSize(bookId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, result);
    }

    //페이지 조회 with 컨텐츠, url
    @PostMapping("/all")
    ResponseEntity<?> getPageListWithContent(@RequestBody PageFindDto pageFindDto) {
        int userId = Auth.getInfo().getUserId();
        log.info("getPageListWithContent 컨트롤러 호출 ");
        List<PageDetailDto> result = pageService.getPageAll(userId, pageFindDto.getBookId());

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, result);
    }

}
