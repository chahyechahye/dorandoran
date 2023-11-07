package com.doran.raw_voice.repository;

import java.util.List;

import com.doran.raw_voice.dto.res.RawVoiceResDto;
import com.doran.utils.common.Genders;

public interface RawVoiceRepositoryCustom {
    List<RawVoiceResDto> findRawVoiceByUserId(int userId, Genders genders);
}
