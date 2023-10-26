package com.doran.raw_voice.repository;

import com.doran.raw_voice.entity.RawVoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawVoiceRepository extends JpaRepository<RawVoice, Integer>, RawVoiceRepositoryCustom {
}
