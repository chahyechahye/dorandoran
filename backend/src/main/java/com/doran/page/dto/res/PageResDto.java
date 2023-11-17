package com.doran.page.dto.res;

import com.doran.content.dto.res.ContentResDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PageResDto {
    private int pageId;
    private int idx;
    private String imgUrl;
}
