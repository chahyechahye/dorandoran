package com.doran.content.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doran.content.dto.res.ContentResDto;
import com.doran.content.service.ContentService;
import com.doran.page.service.PageService;
import com.doran.response.CommonResponseEntity;
import com.doran.response.SuccessCode;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final PageService pageService;

    // 컨텐츠 등록

    //컨텐츠 조회(동화 낭독), (동화책 Id, 페이지 idx)
    @GetMapping("/{book_id}/{idx}")
    ResponseEntity<?> getContent(@PathVariable(value = "book_id") int bookId, @PathVariable int idx) {
        //int userId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        int userId = 1; //임시떔빵

        //idx로 pageId 추출 (idx)
        int pageId = pageService.findPageIdByIdxAndBookId(bookId,idx);
        List<ContentResDto> findContent = contentService.getContentWithVoice(userId, pageId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, findContent);
    }
}
