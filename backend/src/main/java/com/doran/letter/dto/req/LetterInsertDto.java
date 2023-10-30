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
    private int receiverId; // 편지를 받는 사람 id
    private int senderId; // 편지를 보내는 사람이 아이일 경우에만 같이 보냄
}
