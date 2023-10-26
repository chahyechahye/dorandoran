package com.doran.raw_voice;

import com.doran.raw_voice.dto.req.RawVoiceInsertDto;
import com.doran.raw_voice.dto.res.RawVoiceListDto;
import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.entity.RawVoice;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2023-10-26T12:41:39+0900",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class RawVoiceMapperImpl implements RawVoiceMapper {
//    @Override
//    RawVoice voiceInsertToRawVoice(RawVoiceInsertDto rawVoiceInsertDto, String voiceUrl){
//
//}
    @Override
    public RawVoiceResDto rawVoiceToResDto(RawVoice rawVoice){
        if(rawVoice == null) return null;
        RawVoiceResDto rawVoiceResDto = new RawVoiceResDto();
        rawVoiceResDto.setRvId(String.valueOf(rawVoice.getId()));
        rawVoiceResDto.setVoiceUrl(rawVoice.getVoiceUrl());
        return rawVoiceResDto;
    }
    @Override
    public List<RawVoiceResDto> toDtoList(List<RawVoice> rawVoiceList){
        if(rawVoiceList != null) return null;
        List<RawVoiceResDto> rawVoiceResDtoList = new ArrayList<>();
        for(RawVoice rawVoice : rawVoiceList) rawVoiceResDtoList.add(rawVoiceToResDto(rawVoice));
        return rawVoiceResDtoList;
    }
}
