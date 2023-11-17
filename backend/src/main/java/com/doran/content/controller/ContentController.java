package com.doran.content.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doran.content.dto.req.ContentInsertDto;
import com.doran.content.dto.res.ContentResDto;
import com.doran.content.service.ContentService;
import com.doran.page.entity.Page;
import com.doran.page.service.PageService;
import com.doran.parent.service.ParentService;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.UserInfo;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/content")
@RequiredArgsConstructor
@Slf4j
public class ContentController {
    private final ContentService contentService;
    private final PageService pageService;
    private final ParentService parentService;

    // 컨텐츠 등록
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{book_id}")
    ResponseEntity<?> insertContent(@PathVariable(value = "book_id") int bookId,
        @RequestBody ContentInsertDto contentInsertDto) {
        int idx = contentInsertDto.getIdx();
        String script = contentInsertDto.getScript();

        Page findPage = pageService.findPageIdByIdxAndBookId(bookId, idx);
        contentService.insertContent(findPage, script);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }

    //컨텐츠 조회(동화 낭독), (동화책 Id, 페이지 idx)
    @GetMapping("/{book_id}/{idx}")
    ResponseEntity<?> getContent(@PathVariable(value = "book_id") int bookId, @PathVariable int idx) {
        log.info("getContent 컨트롤러 호출");

        UserInfo userInfo = Auth.getInfo();
        int pageId = pageService.findPageIdByIdxAndBookId(bookId, idx).getId();
        int parentUserId = parentService.getParentUserId(userInfo.getUserId(), userInfo.getUserRole().getRole());

        log.info("부모의 유저 아이디 : " + parentUserId);
        List<ContentResDto> findContent = contentService.getContentWithVoice(parentUserId, pageId, null);

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, findContent);
    }

    //따옴표 전부 치환
    @PutMapping()
    public ResponseEntity update() {
        contentService.updateContent();

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK);
    }
}
