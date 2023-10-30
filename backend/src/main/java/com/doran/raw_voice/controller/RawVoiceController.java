package com.doran.raw_voice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
