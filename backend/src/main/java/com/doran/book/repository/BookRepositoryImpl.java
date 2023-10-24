package com.doran.book.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
}
