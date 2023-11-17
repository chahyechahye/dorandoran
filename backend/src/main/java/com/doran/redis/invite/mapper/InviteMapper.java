package com.doran.redis.invite.mapper;

import org.mapstruct.Mapper;

import com.doran.redis.invite.key.Invite;

@Mapper(componentModel = "spring")
public interface InviteMapper {
    Invite toInvite(String code, int userId);
}
