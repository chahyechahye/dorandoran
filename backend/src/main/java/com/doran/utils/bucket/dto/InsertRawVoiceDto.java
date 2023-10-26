package com.doran.utils.bucket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class InsertRawVoiceDto {
    private String fileName;
    private MultipartFile file;
}
