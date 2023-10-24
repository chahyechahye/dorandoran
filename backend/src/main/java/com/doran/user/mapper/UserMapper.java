package com.doran.user.mapper;

import org.mapstruct.Mapper;

import com.doran.user.entity.User;
import com.doran.user.type.Roles;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(String name, Roles userRole);

}
