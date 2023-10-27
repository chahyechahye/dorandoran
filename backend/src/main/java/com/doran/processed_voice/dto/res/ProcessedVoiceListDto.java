package com.doran.processed_voice.dto.res;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProcessedVoiceListDto {
    int size;
    List<ProcessedVoiceResDto> processedVoiceResDtoList;
}
