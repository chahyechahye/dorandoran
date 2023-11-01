package com.doran.processed_voice.repository;

import static com.doran.processed_voice.entity.QProcessedVoice.*;
import static com.doran.user.entity.QUser.*;

import java.util.Optional;

import com.doran.processed_voice.entity.ProcessedVoice;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessedVoiceRepositoryCustomImpl implements ProcessedVoiceRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public Optional<ProcessedVoice> findVoiceByParentIdAndContentId(int userId, int contentId){
        return Optional.ofNullable(jpaQueryFactory
                .select(processedVoice)
                .from(processedVoice)
                .where(processedVoice.content.id.eq(contentId),
                    processedVoice.user.id.eq(userId))
                .fetchOne());
    }
}
