package com.doran.processed_voice.dto.req;

import org.springframework.web.multipart.MultipartFile;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProcessedVoiceInsertDto {
    private int contentId;
    private int userId; // 누구 목소리로 변형된 것인지 판별
    private Genders gender;
    private MultipartFile file;
}
