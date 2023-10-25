package com.doran.raw_voice.dto.res;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RawVoiceResDto {
    String rvId;
    String userId;
    String voiceUrl;
}
