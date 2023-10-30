package com.doran.letter.dto.req;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LetterInsertDto {
    private String title;
    private MultipartFile content;
    private int profileId; // 편지 보내는 사람이 아이일 경우, 아니라면 null
    private int receiverId; // 편지를 받는 사람 id (ParentService에 따라 부모인지 아이인지 달라짐)
}
