package com.doran.processed_voice.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProcessedVoiceResDto {
    int contentId;
    String voiceUrl;
}
