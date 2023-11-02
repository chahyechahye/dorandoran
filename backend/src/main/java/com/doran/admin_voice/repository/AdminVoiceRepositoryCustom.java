package com.doran.admin_voice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.admin_voice.entity.AdminVoice;


public interface AdminVoiceRepositoryCustom {
    List<AdminVoice> findAdminVoiceByContentId();
    AdminVoice findMaleAdminVoiceByContentId();
    AdminVoice findFemaleAdminVoiceByContentId();
}
