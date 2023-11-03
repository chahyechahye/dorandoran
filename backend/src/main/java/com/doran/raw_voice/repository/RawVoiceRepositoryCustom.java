package com.doran.raw_voice.repository;

import java.util.List;

import com.doran.raw_voice.dto.res.RawVoiceResDto;

public interface RawVoiceRepositoryCustom {
    List<RawVoiceResDto> findRawVoiceByUserId(int userId);
}
