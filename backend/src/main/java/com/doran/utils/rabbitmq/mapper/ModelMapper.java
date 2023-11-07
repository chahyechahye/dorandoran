package com.doran.utils.rabbitmq.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.utils.common.Genders;
import com.doran.utils.rabbitmq.dto.req.ModelReqMessage;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    @Mapping(source = "genders", target = "gender")
    ModelReqMessage toReqMessage(int userId, List<RawVoiceResDto> rawVoiceList, Genders genders);
}
