package com.doran.letter.dto.res;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LetterResDto {
    private Integer letterId;
    private String contentUrl;
    private LocalDateTime createdDate; // 작성 날짜
}