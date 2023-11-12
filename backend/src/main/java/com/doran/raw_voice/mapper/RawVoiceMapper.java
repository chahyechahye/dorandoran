package com.doran.raw_voice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.raw_voice.dto.res.RawVoiceListDto;
import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.entity.RawVoice;
import com.doran.user.entity.User;
import com.doran.utils.common.Genders;

@Mapper(componentModel = "spring")
public interface RawVoiceMapper {
    //    RawVoice voiceInsertToRawVoice(RawVoiceInsertDto rawVoiceInsertDto, String voiceUrl);
    @Mapping(source = "id", target = "rvId")
    @Mapping(source = "voiceGender", target = "gender")
    RawVoiceResDto rawVoiceToResDto(RawVoice rawVoice);

    RawVoiceListDto listToResListDto(List<RawVoiceResDto> rawVoiceResDtoList, int size);

    List<RawVoiceResDto> toDtoList(List<RawVoice> rawVoiceList);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "gender", target = "voiceGender")
    RawVoice voiceInsertToRawVoice(User user, String voiceUrl, Genders gender);

    default List<String> toList(List<RawVoice> list) {
        return list.stream()
            .map(RawVoice::getVoiceUrl)
            .toList();
    }
}
