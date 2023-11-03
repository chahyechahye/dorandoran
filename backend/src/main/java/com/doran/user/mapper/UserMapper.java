package com.doran.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.entity.User;
import com.doran.user.type.Roles;
import com.doran.utils.common.UserInfo;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(String name, Roles userRole);

    @Mapping(target = "childId", ignore = true)
    @Mapping(target = "parentId", ignore = true)
    @Mapping(target = "provider", ignore = true)
    UserTokenBaseDto toUserTokenBaseDto(UserInfo userInfo);

}
