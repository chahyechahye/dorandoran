package com.doran.raw_voice.mapper;

import com.doran.raw_voice.dto.req.RawVoiceInsertDto;
import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.entity.RawVoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RawVoiceMapper {
//    RawVoice voiceInsertToRawVoice(RawVoiceInsertDto rawVoiceInsertDto, String voiceUrl);
    @Mapping(source="id", target="rvId")
    RawVoiceResDto rawVoiceToResDto(RawVoice rawVoice);
    List<RawVoiceResDto> toDtoList(List<RawVoice> rawVoiceList);
}
