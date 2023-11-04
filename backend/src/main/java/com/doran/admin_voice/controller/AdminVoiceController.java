package com.doran.admin_voice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doran.admin_voice.dto.req.AdminVoiceInsertDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDto;
import com.doran.admin_voice.service.AdminVoiceService;
import com.doran.utils.common.Genders;
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

    // content 단위 관리자 목소리
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{content_id}")
    public ResponseEntity<?> getAdminVoiceByContentId(@PathVariable(value="content_id") int contentId,
                                                @RequestParam(name="gender") Genders gender){
        AdminVoiceResDto adminVoiceResDto = adminVoiceService.findAdminVoiceByContentId(gender,contentId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,adminVoiceResDto);
    }
    // book 단위 관리자 목소리
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/book/{book_id}")
    public ResponseEntity<?> getAdminVoiceByBookId(@PathVariable(value="book_id") int bookId,
                                                @RequestParam(name="gender") Genders gender){
        log.info("Query start " + bookId + " " + gender);
        List<AdminVoiceResDto> adminVoiceResDtoList = adminVoiceService.findAdminVoiceByBookId(gender,bookId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,adminVoiceResDtoList);
    }
    // 관리자 목소리 등록
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> insertAdminVoice(AdminVoiceInsertDto adminVoiceInsertDto){
        adminVoiceService.insertAdminVoice(adminVoiceInsertDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }
}