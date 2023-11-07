package com.doran.utils.rabbitmq.dto.req;

import java.util.List;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.raw_voice.entity.RawVoice;
import com.doran.utils.common.Genders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ModelReqMessage {
    private int userId;
    private Genders gender;
    private List<RawVoiceResDto> rawVoiceList;
}
