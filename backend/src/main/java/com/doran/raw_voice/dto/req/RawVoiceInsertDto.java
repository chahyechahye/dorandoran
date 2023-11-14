package com.doran.raw_voice.dto.req;

import org.springframework.web.multipart.MultipartFile;

import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RawVoiceInsertDto {
    private MultipartFile file;
    private Genders gender;
    private String title;
    private int scriptNum;
}
