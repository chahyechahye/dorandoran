package com.doran.letter.dto.res;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LetterResDtoList {
    private Integer size;
    private List<LetterResDto> letterResDtoList;
}
