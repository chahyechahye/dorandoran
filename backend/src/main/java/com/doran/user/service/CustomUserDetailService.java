package com.doran.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doran.parent.type.Provider;
import com.doran.user.dto.req.UserTokenBaseDto;
import com.doran.user.type.Roles;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailService {

	public UserDetails loadUserByUsername(Claims userInfo) throws UsernameNotFoundException {
		Object userRole = userInfo.get("userRole");
		Object provider = userInfo.get("provider");
		Provider provider1 = Provider.valueOf(provider.toString());
		Roles roles = Roles.valueOf(userRole.toString());

		int userId = (int)userInfo.get("userId");
		int parentId = (int)userInfo.get("parentId");
		// int childId = (int)userInfo.get("childId");
		// int selectProfileId = (int)userInfo.get("selectProfileId");
		log.info("userId : {}", userId);
		log.info("parentId : {}", parentId);
		// log.info("childId : {}", childId);
		// log.info("selectProfileId : {}", selectProfileId);

		UserTokenBaseDto build = UserTokenBaseDto.builder()
			.userId(userId)
			// .childId(childId)
			.parentId(parentId)
			// .selectProfileId(selectProfileId)
			.provider(provider1)
			.userRole(roles)
			.build();
		log.info(build.toString());

		return build;
	}
}
