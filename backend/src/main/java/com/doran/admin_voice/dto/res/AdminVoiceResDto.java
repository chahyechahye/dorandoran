package com.doran.admin_voice.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminVoiceResDto {
    private int contentId; // 필요없을 거 같긴 한데 일단 넣어둠
    private String voiceUrl;
}
