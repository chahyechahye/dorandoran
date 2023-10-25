package com.doran.content.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContentResDto {
    private int content_id;
    private String script;
    private int pv_id; //processed_id
    private int voiceUrl;
}
