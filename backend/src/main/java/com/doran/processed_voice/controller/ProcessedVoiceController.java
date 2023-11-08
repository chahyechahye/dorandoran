package com.doran.processed_voice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doran.parent.service.ParentService;
import com.doran.processed_voice.dto.req.PVNewInsertDto;
import com.doran.processed_voice.dto.req.ProcessedVoiceInsertDto;
import com.doran.processed_voice.service.ProcessedVoiceService;
import com.doran.raw_voice.dto.res.RecordCheckDto;
import com.doran.utils.auth.Auth;
import com.doran.utils.common.Genders;
import com.doran.utils.common.UserInfo;
import com.doran.utils.rabbitmq.service.VoicePubService;
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
    private final VoicePubService voicePubService;
    private final ParentService parentService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getProcessedVoiceList() {
        log.info("가공된 목소리 조회");
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,
            processedVoiceService.getProcessedVoiceList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // admin Test 전용
    @GetMapping("/{content_id}/{parent_user_id}")
    public ResponseEntity<?> getProcessedVoiceForAdmin(@PathVariable(value = "content_id") int contentId,
        @PathVariable(value = "parent_user_id") int userId) {
        log.info("가공된 목소리 검색 - 관리자용");
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,
            processedVoiceService.getProcessedVoiceForAdmin(userId, contentId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CHILD')") // 아이가 동화책 읽을 때 목소리 호출
    @GetMapping("/{content_id}")
    public ResponseEntity<?> getProcessedVoice(@PathVariable(value = "content_id") int contentId) {
        log.info("가공된 목소리 검색");
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,
            processedVoiceService.getProcessedVoiceById(contentId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> insertProcessedVoice(ProcessedVoiceInsertDto processedVoiceInsertDto) {
        log.info("가공된 목소리 등록");
        processedVoiceService.insertProcessedVoice(processedVoiceInsertDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }

    // 동화가 추가 될 때, 가공 목소리 추가 등록
    @PostMapping("/new")
    public ResponseEntity<?> insertNewProcessedVoice(@RequestBody PVNewInsertDto pvNewInsertDto) {
        log.info("새로운 동화책의 목소리 등록");

        int userId = Auth.getInfo().getUserId();
        voicePubService.sendMessage(userId, pvNewInsertDto.getBookId(), pvNewInsertDto.getGenders());

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE);
    }

    //낭독 체킹 API
    @GetMapping("/check")
    public ResponseEntity<?> checkRecord() {
        log.info("녹음 체킹 컨트롤러");

        UserInfo info = Auth.getInfo();

        int parentUserId = parentService.getParentUserId(info.getUserId(), String.valueOf(info.getUserRole()));
        processedVoiceService.checkRecording(parentUserId);

        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_CODE,
            new RecordCheckDto(Boolean.TRUE, Boolean.FALSE));
    }
}
