package com.doran.user.mapper;

import org.mapstruct.Mapper;

import com.doran.user.dto.req.UserJoinDto;
import com.doran.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(UserJoinDto userJoinDto);

}
