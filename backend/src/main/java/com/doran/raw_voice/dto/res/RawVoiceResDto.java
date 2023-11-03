package com.doran.raw_voice.dto.res;

import com.doran.utils.common.Genders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RawVoiceResDto {
    private int rvId;
    private String voiceUrl;
    private Genders gender;
}
