package com.doran.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doran.user.type.Roles;
import com.doran.utils.common.UserInfo;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailService {

	public UserDetails loadUserByUsername(Claims userInfo) throws UsernameNotFoundException {
		Object userRole = userInfo.get("userRole");
		Roles roles = Roles.valueOf(userRole.toString());

		int userId = (int)userInfo.get("userId");
		int parentId = (int)userInfo.get("parentId");
		// int selectProfileId = (int)userInfo.get("selectProfileId");
		log.info("userId : {}", userId);
		log.info("parentId : {}", parentId);
		// log.info("selectProfileId : {}", selectProfileId);

		UserInfo build = UserInfo.builder()
			.userId(userId)
			.selectProfileId(null)
			.userRole(roles)
			.build();
		log.info(build.toString());

		return build;
	}
}
