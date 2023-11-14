package com.doran.record_book.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.book.entity.Book;
import com.doran.book.service.BookService;
import com.doran.content.service.ContentService;
import com.doran.page.entity.Page;
import com.doran.page.service.PageService;
import com.doran.record_book.dto.res.RecordBookResDto;
import com.doran.record_book.dto.res.ScriptResDto;
import com.doran.record_book.service.RecordBookService;
import com.doran.redis.script.key.Script;
import com.doran.redis.script.mapper.ScriptMapper;
import com.doran.redis.script.service.ScriptService;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.UserInfo;
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
    private final BookService bookService;
    private final PageService pageService;
    private final ContentService contentService;
    private final ScriptService scriptService;
    private final ScriptMapper scriptMapper;

    //스크립트 전체 조회
    @GetMapping()
    public ResponseEntity findScript() {
        long start = System.currentTimeMillis();

        RecordBookResDto bookTitleList = recordBookService.findBookTitleList();

        log.info("걸린 시간(ms) : {}", System.currentTimeMillis() - start);

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, bookTitleList);
    }

    //스크립트 등록
    @PostMapping("/{book-id}")
    public ResponseEntity postScript(@PathVariable("book-id") int bookId) {
        Book findBook = bookService.findBookById(bookId);
        List<Page> pageList = pageService.findPageByBookId(bookId);

        List<String> contentByPageList = contentService.findContentByPageList(pageList);

        recordBookService.regist(contentByPageList, findBook.getTitle());
        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, "등록 완료");
    }

    @GetMapping("/save")
    public ResponseEntity getScript() {
        UserInfo info = Auth.getInfo();
        log.info("요청 들어옴");
        Script script = scriptService.findScript(String.valueOf(info.getUserId()));
        log.info("script : {}", script);
        ScriptResDto scriptResDto = scriptMapper.toScriptResDto(script);
        log.info("scriptResDto : {}", scriptResDto);
        scriptService.delete(String.valueOf(info.getUserId()));
        log.info("삭제 진행");

        return CommonResponseEntity.getResponseEntity(SuccessCode.OK, scriptResDto);

    }
}
