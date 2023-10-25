package com.doran.user.repository;

import static com.doran.parent.entity.QParent.*;
import static com.doran.user.entity.QUser.*;

import java.util.Optional;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<UserTokenBaseDto> findUser(String email, Provider provider) {
		return Optional.ofNullable(jpaQueryFactory
			.select(Projections.fields(UserTokenBaseDto.class,
				user.id.as("userId"),
				parent.id.as("parentId"),
				parent.provider,
				user.userRole))
			.from(parent)
			.join(parent.user, user)
			.where(parent.email.eq(email),
				parent.provider.eq(provider))
			.fetchOne());
	}
}
