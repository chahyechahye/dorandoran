package com.doran.admin_voice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.admin_voice.entity.AdminVoice;
import com.doran.admin_voice.type.Genders;

public interface AdminVoiceRepositoryCustom {
    // 컨텐츠 단위 남성 관리자 목소리 호출
    Optional<AdminVoice> findAdminVoiceByContentId(Genders gender, int contentId);

    // 책 단위 남성 관리자 목소리 호출
    List<AdminVoice> findAdminVoiceByBookId(Genders gender, int bookId);
}
