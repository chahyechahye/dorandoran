package com.doran.user.repository;

import java.util.Optional;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserTokenBaseDto;

public interface UserRepositoryCustom {
	Optional<UserTokenBaseDto> findUser(String email, Provider provider);
}
