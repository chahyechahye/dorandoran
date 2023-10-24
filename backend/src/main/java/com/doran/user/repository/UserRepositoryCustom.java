package com.doran.user.repository;

import java.util.Optional;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserFindDto;

public interface UserRepositoryCustom {
	Optional<UserFindDto> findUser(String email, Provider provider);
}
