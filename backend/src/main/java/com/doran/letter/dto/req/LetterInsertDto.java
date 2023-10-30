package com.doran.letter.dto.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LetterInsertDto {
    private String title;
    private MultipartFile content;
    private int receiverId; // 편지를 받는 사람 id (ParentService에 따라 부모인지 아이인지 달라짐)
}
