package com.doran.admin_voice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.admin_voice.dto.req.AdminVoiceInsertDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDto;
import com.doran.admin_voice.entity.AdminVoice;
import com.doran.content.entity.Content;

@Mapper(componentModel = "spring")
public interface AdminVoiceMapper {
    @Mapping(source="content.id", target="contentId")
    AdminVoiceResDto toResDto(AdminVoice adminVoice);
    List<AdminVoiceResDto> toResDtoList(List<AdminVoice> adminVoiceList);
    @Mapping(target="id", ignore = true)
    AdminVoice dtoToEntity(AdminVoiceInsertDto adminVoiceInsertDto, String voiceUrl, Content content);
}
