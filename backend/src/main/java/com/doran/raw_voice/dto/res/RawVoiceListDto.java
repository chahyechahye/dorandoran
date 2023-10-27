package com.doran.raw_voice.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RawVoiceListDto {
    private int size;
    private List<RawVoiceResDto> rawVoiceResDtoList;
}
