package com.doran.processed_voice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doran.processed_voice.entity.ProcessedVoice;

@Repository
public interface ProcessedVoiceRepository implements JpaRepository<ProcessedVoice, Integer>, ProcessedVoiceRepositoryCustom {
}
