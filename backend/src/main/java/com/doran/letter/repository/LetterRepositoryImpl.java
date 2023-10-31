package com.doran.letter.repository;

import static com.doran.letter.entity.QLetter.*;

import java.util.List;

import com.doran.letter.entity.Letter;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LetterRepositoryImpl implements LetterRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Letter> findAllLetterByParentId(int parentId){ // 부모가 받은 모든 편지 (일단 안씀)
        return jpaQueryFactory
            .select(letter)
            .from(letter)
            .where(letter.parent.id.eq(parentId))
            .fetch();
    }
    @Override
    public Letter findLetterByParentId(int parentId){ // 부모가 받은 편지 (프론트 요청마다 보낼 예정)
        return jpaQueryFactory
            .selectFrom(letter)
            .where(letter.receiverId.eq(parentId),
                letter.createdDate.eq(letter.modifiedDate))
            .limit(1)
            .fetchOne();
    }
    @Override
    public List<Letter> findAllLetterByProfileId(int profileId){
        return jpaQueryFactory
            .select(letter)
            .from(letter)
            .where(letter.profile.id.eq(profileId))
            .fetch();

    }
    @Override
    public Letter findLetterByProfileId(int profileId){ // 아이가 받은 편지
        return jpaQueryFactory
            .selectFrom(letter)
            .where(letter.receiverId.eq(profileId),
                letter.createdDate.eq(letter.modifiedDate))
            .limit(1)
            .fetchOne();
    }
}
