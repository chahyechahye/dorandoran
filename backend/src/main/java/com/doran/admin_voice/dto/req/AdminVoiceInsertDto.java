package com.doran.admin_voice.dto.req;

import org.springframework.web.multipart.MultipartFile;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminVoiceInsertDto {
    private int contentId;
    private Genders gender; // MALE or FEMALE
    private MultipartFile file;
}
