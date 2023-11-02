package com.doran.admin_voice.repository;

import java.util.List;

import com.doran.admin_voice.entity.AdminVoice;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminVoiceRepositoryCustomImpl implements AdminVoiceRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    public List<AdminVoice> findAdminVoiceByContentId(){
        return null;
    }
    public AdminVoice findMaleAdminVoiceByContentId(){
        return null;
    }
    public AdminVoice findFemaleAdminVoiceByContentId(){
        return null;
    }
}
