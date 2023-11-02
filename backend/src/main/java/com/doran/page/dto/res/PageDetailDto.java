package com.doran.page.dto.res;

import com.doran.content.dto.res.ContentResDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageDetailDto {
    private int pageId;
    private int idx;
    private String imgUrl;
    private ContentResDto contentResDto;
}
