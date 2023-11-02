package com.doran.admin_voice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doran.admin_voice.entity.AdminVoice;

@Repository

public interface AdminVoiceRepository extends JpaRepository<AdminVoice, Integer>, AdminVoiceRepositoryCustom{
}
