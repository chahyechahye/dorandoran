package com.doran.admin_voice.dto.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminVoiceInsertDto {
    private int contentId;
    private MultipartFile file;
}
