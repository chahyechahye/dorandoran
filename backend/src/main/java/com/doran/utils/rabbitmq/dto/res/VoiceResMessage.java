package com.doran.utils.rabbitmq.dto.res;

import java.util.ArrayList;
import java.util.List;

import com.doran.processed_voice.dto.res.PVQueResDto;

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
public class VoiceResMessage {
    private int userId;
    private List<PVQueResDto> pvList;
}
