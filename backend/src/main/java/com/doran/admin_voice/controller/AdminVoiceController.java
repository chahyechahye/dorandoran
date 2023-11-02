package com.doran.admin_voice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.admin_voice.dto.req.AdminVoiceInsertDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDtoList;
import com.doran.admin_voice.service.AdminVoiceService;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin_voice")
public class AdminVoiceController {
    private final AdminVoiceService adminVoiceService;

    // content 단위 남성 관리자 목소리
    @GetMapping("/male/{content_id}")
    public ResponseEntity<?> getMaleAdminVoiceByContentId(@PathVariable(value="content_id") int contentId){
        AdminVoiceResDto adminVoiceResDto = adminVoiceService.findMaleAdminVoiceByContentId(contentId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,adminVoiceResDto);
    }
    // content 단위 여성 관리자 목소리
    @GetMapping("/female/{content_id}")
    public ResponseEntity<?> getFemaleAdminVoiceByContentId(@PathVariable(value="content_id") int contentId){
        AdminVoiceResDto adminVoiceResDto = adminVoiceService.findFemaleAdminVoiceByContentId(contentId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,adminVoiceResDto);
    }
    // book 단위 남성 관리자 목소리
    @GetMapping("/male/{book_id}")
    public ResponseEntity<?> getMaleAdminVoiceByBookId(@PathVariable(value="book_id") int bookId){
        AdminVoiceResDtoList adminVoiceResDtoList = adminVoiceService.findMaleAdminVoiceByBookId(bookId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,adminVoiceResDtoList);
    }
    // book 단위 여성 관리자 목소리
    @GetMapping("/female/{book_id}")
    public ResponseEntity<?> getFemaleAdminVoiceByBookId(@PathVariable(value="book_id") int bookId){
        AdminVoiceResDtoList adminVoiceResDtoList = adminVoiceService.findFemaleAdminVoiceByBookId(bookId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,adminVoiceResDtoList);
    }

}