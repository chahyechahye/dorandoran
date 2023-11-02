package com.doran.admin_voice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.admin_voice.dto.res.AdminVoiceResDto;
import com.doran.admin_voice.dto.res.AdminVoiceResDtoList;
import com.doran.admin_voice.entity.AdminVoice;

@Mapper(componentModel = "spring")
public interface AdminVoiceMapper {
    @Mapping(source="content.id", target="contentId")
    AdminVoiceResDto toResDto(AdminVoice adminVoice);
    AdminVoiceResDtoList toResDtoList(List<AdminVoiceResDto> adminVoiceResDtoList,int size);

}
