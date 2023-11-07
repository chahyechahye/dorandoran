package com.doran.letter.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.letter.dto.req.LetterInsertDto;
import com.doran.letter.dto.req.LetterReadDto;
import com.doran.letter.dto.res.LetterResDtoList;
import com.doran.letter.entity.Letter;
import com.doran.letter.service.LetterService;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.UserInfo;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/letter")
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;
    // 읽지 않은 편지 조회
    @GetMapping("")
    public ResponseEntity<?> getLetterList(){
        log.info("편지 검색");
        UserInfo userInfo= Auth.getInfo();
        LetterResDtoList letterResDtoList = letterService.getLetterList(userInfo.getUserId());
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, letterResDtoList);
    }
    // 읽은 편지 갱신
    @PostMapping("/read")
    public ResponseEntity<?> getLetterCount(@RequestBody LetterReadDto letterReadDto){
        log.info("읽은 편지 갱신!");
        UserInfo userInfo = Auth.getInfo();
        letterService.readLetter(userInfo.getUserId());
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }

    // 편지 등록
    @PostMapping("")
    public ResponseEntity<?> insertLetter(LetterInsertDto letterInsertDto){
        log.info("편지 등록");
        Letter letter = letterService.insertLetter(letterInsertDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, null);
    }
}
