package com.doran.utils.rabbitmq.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.utils.rabbitmq.dto.req.ModelReqMessage;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    ModelReqMessage toReqMessage(int userId, List<RawVoiceResDto> rawVoiceList);
}
