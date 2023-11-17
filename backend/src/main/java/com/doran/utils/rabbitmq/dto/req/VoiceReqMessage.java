package com.doran.utils.rabbitmq.dto.req;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doran.admin_voice.dto.res.AdminFindResDto;
import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VoiceReqMessage {
    private int userId;
    private Genders genders;
    private List<AdminFindResDto> list;
}
