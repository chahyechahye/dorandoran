package com.doran.user.repository;

import java.util.List;
import java.util.Optional;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.entity.User;
import com.doran.user.type.Roles;
import com.doran.utils.common.UserInfo;

public interface UserRepositoryCustom {
    Optional<UserTokenBaseDto> findUser(String email, Provider provider);

    Optional<UserTokenBaseDto> findUser(int childId, int profileId);

    Optional<UserInfo> findUser(int userId, Roles roles);

    Optional<UserInfo> findUser(int userId, int profileId, Roles roles);

    Optional<User> findUserByParentId(int parentId);

    Optional<User> findUserByProfileId(int profileId);

    List<User> findAllUser();
}
