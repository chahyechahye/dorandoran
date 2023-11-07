package com.doran.utils.rabbitmq.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.admin_voice.dto.res.AdminFindResDto;
import com.doran.utils.common.Genders;
import com.doran.utils.rabbitmq.dto.req.VoiceReqMessage;

@Mapper(componentModel = "spring")
public interface VoiceMapper {
    VoiceReqMessage toReqMessage(int userId, List<AdminFindResDto> list, Genders genders);
}
