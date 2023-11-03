package com.doran.raw_voice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.raw_voice.dto.req.RawVoiceInsertDto;
import com.doran.raw_voice.dto.res.RawVoiceListDto;
import com.doran.raw_voice.service.RawVoiceService;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/voice")
@Slf4j
public class RawVoiceController {
    private final RawVoiceService rawVoiceService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getRawVoiceList() {
        log.info("Raw Voice (원본 목소리) 리스트 호출");
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, rawVoiceService.getRawVoiceAll());
    }
    // 유저 목소리 검색
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{user_id}")
    public ResponseEntity<?> getRawVoiceListByUserId(@PathVariable(value="user_id") int userId){
        RawVoiceListDto rawVoiceListDto = rawVoiceService.getRawVoiceByUserId(userId);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, rawVoiceListDto);
    }
    // 원본 목소리 등록 (부모 실제 녹음)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PARENT')") // 관리자, 부모만 등록 가능
    @PostMapping("")
    public ResponseEntity<?> insertRawVoice(RawVoiceInsertDto rawVoiceInsertDto) {
        log.info("Raw Voice (원본 목소리) 추가");
        rawVoiceService.insertRawVoice(rawVoiceInsertDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,null);
    }
}
