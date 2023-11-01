package com.doran.processed_voice.dto.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProcessedVoiceInsertDto {
    private int contentId; // 목소리 등록할 땐 null 혹은 그외 다른 값, 낭독할 땐 동화책 컨텐츠 id 반환
    private int userId;
    private MultipartFile voice;
}
