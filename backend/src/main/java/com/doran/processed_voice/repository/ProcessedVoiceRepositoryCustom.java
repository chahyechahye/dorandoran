package com.doran.processed_voice.repository;

import java.util.Optional;

import com.doran.processed_voice.entity.ProcessedVoice;

public interface ProcessedVoiceRepositoryCustom {
    public Optional<ProcessedVoice> findVoiceByParentIdAndContentId(int userId, int contentId);
}
