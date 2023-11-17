package com.doran.user.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doran.user.repository.UserRepository;
import com.doran.user.type.Roles;
import com.doran.utils.common.UserInfo;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(Claims userInfo) throws UsernameNotFoundException {
        UserInfo build;

        Roles roles = Roles.valueOf(userInfo.get("userRole").toString());
        int userId = (int)userInfo.get("userId");
        //role이 아이일 때
        if (roles.equals(Roles.CHILD)) {
            log.info("아이 토큰");
            int selectProfileId = (int)userInfo.get("selectProfileId");
            Optional<UserInfo> findUser = userRepository.findUser(userId, selectProfileId, roles);

            build = validUserInfo(findUser);
        } else {
            log.info("부모 or 관리자 토큰");
            Optional<UserInfo> findUser = userRepository.findUser(userId, roles);

            build = validUserInfo(findUser);
        }

        log.info("userInfo : {}", build);

        return build;
    }

    public UserInfo validUserInfo(Optional<UserInfo> findUser) {
        return findUser
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
