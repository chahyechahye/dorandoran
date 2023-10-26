package com.doran.raw_voice.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @RequiredArgsConstructor
public class RawVoiceInsertDto {
    private MultipartFile voiceUrl;
}
