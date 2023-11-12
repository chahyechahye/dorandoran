package com.doran.record_book.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) //null 이면 안나오게 함
public class ScriptResDto {
    private String script;

    private Integer scriptNum;
}
