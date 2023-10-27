package com.doran.user.service;

import java.util.Optional;

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
        UserInfo build = new UserInfo();

        Roles roles = Roles.valueOf(userInfo.get("userRole").toString());
        build.setUserRole(roles);
        build.setUserId((int)userInfo.get("userId"));
        //있으면 넣고 없으면 안넣음
        Optional.ofNullable(userInfo.get("selectProfileId"))
            .ifPresent(v -> build.setSelectProfileId((int)v));

        log.info("userInfo : {}", build);

        return build;
    }
}
