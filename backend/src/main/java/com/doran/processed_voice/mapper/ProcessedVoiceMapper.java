package com.doran.processed_voice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.content.entity.Content;
import com.doran.processed_voice.dto.res.ProcessedVoiceResDto;
import com.doran.processed_voice.entity.ProcessedVoice;
import com.doran.user.entity.User;

@Mapper(componentModel = "spring")
public interface ProcessedVoiceMapper {
    @Mapping(source="id", target="pvId")
    @Mapping(source="content.id", target="contentId")
    ProcessedVoiceResDto pvToResDto(ProcessedVoice processedVoice);
    List<ProcessedVoiceResDto> toDtoList(List<ProcessedVoice> processedVoiceList);
    ProcessedVoice toProcessedVoice(Content content, User user, String voiceUrl);

}
