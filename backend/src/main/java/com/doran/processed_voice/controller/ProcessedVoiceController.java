package com.doran.processed_voice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.content.entity.Content;
import com.doran.content.repository.ContentRepository;
import com.doran.content.service.ContentService;
import com.doran.processed_voice.dto.req.ProcessedVoiceInsertDto;
import com.doran.processed_voice.service.ProcessedVoiceService;
import com.doran.user.entity.User;
import com.doran.user.service.UserService;
import com.doran.utils.auth.Auth;
import com.doran.utils.response.CommonResponseEntity;
import com.doran.utils.response.SuccessCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/content/voice")
@RequiredArgsConstructor
public class ProcessedVoiceController {
    private final ProcessedVoiceService processedVoiceService;
    //private final ContentService contentService; // 가공된 목소리 저장을 위해 필요
    //private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getProcessedVoiceList() {
        log.info("가공된 목소리 조회");
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, processedVoiceService.getProcessedVoiceList());
    }
    @GetMapping("/{pv_id}")
    public ResponseEntity<?> getProcessedVoice(@PathVariable(value="pv_id") int pvId) {
        log.info("가공된 목소리 검색");
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE, processedVoiceService.getProcessedVoiceById(pvId));
    }
    @PostMapping("")
    public ResponseEntity<?> insertProcessedVoice(ProcessedVoiceInsertDto processedVoiceInsertDto){
        log.info("가공된 목소리 등록");
        processedVoiceService.insertProcessedVoice(processedVoiceInsertDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }
}
