package com.doran.processed_voice.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PVQueResDto {
    private int contentId;
    private String voiceUrl;
}
