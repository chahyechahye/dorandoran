package com.doran.redis.refresh.mapper;

import org.mapstruct.Mapper;

import com.doran.redis.refresh.key.RefreshToken;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {
    RefreshToken toRefreshToken(String value, int userId);
}
