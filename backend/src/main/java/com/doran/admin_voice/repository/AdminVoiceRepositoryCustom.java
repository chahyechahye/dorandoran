package com.doran.admin_voice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.admin_voice.entity.AdminVoice;


public interface AdminVoiceRepositoryCustom {
    // 컨텐츠 단위 남성 관리자 목소리 호출
    Optional<AdminVoice> findMaleAdminVoiceByContentId(int contentId);
    // 컨텐츠 단위 남성 관리자 목소리 호출
    Optional<AdminVoice> findFemaleAdminVoiceByContentId(int contentId);

    // 책 단위 남성 관리자 목소리 호출
    List<AdminVoice> findMaleAdminVoiceByBookId(int bookId);
    // 책 단위 여성 관리자 목소리 호출
    List<AdminVoice> findFemaleAdminVoiceByBookId(int bookId);
}
