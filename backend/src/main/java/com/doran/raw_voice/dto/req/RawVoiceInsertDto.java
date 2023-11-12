package com.doran.raw_voice.dto.req;

import org.springframework.web.multipart.MultipartFile;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawVoiceInsertDto {
    private MultipartFile file;
    private Genders gender;
    private String script;
    private int scriptNum;
}
