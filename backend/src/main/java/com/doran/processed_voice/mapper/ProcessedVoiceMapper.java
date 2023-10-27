package com.doran.processed_voice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.processed_voice.dto.res.ProcessedVoiceResDto;
import com.doran.processed_voice.entity.ProcessedVoice;

@Mapper(componentModel = "spring")
public interface ProcessedVoiceMapper {
    @Mapping(source="content.id", target="contentId")
    ProcessedVoiceResDto pvToResDto(ProcessedVoice processedVoice);
    List<ProcessedVoiceResDto> toDtoList(List<ProcessedVoice> processedVoiceList);

}
