package com.doran.content.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentResDto {
    private int content_id;
    private String script;
    private Integer pv_id; //processed_id
    private String voiceUrl;
}
