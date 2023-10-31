package com.doran.letter.dto.res;

import com.google.type.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LetterResDto {
    private String sender; // 편지 작성자
    private String contentUrl;
    private DateTime createdDate; // 작성 날짜

}
