package com.doran.letter.repository;

import static com.doran.letter.entity.QLetter.*;
import static com.doran.user.entity.QUser.*;

import java.util.List;
import java.util.Optional;

import org.hamcrest.core.IsNull;

import com.doran.letter.entity.Letter;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LetterRepositoryCustomImpl implements LetterRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Letter> findLetterByUserId(int userId){
        return Optional.ofNullable(jpaQueryFactory
            .selectFrom(letter)
            .join(user)
                .on(letter.receiverId.eq(user.id))
            .where(
                letter.receiverId.eq(userId),
                letter.createdDate.eq(letter.modifiedDate))
                .limit(1)
            .fetchOne());
    }
}
