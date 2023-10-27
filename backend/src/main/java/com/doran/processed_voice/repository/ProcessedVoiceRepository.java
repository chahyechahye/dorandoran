package com.doran.processed_voice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.processed_voice.entity.ProcessedVoice;

public interface ProcessedVoiceRepository extends JpaRepository<ProcessedVoice, Integer>, ProcessedVoiceRepositoryCustom {
}
