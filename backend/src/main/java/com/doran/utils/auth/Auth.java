package com.doran.utils.auth;

import org.springframework.security.core.context.SecurityContextHolder;

import com.doran.utils.common.UserInfo;
import com.doran.utils.exception.dto.CustomException;
import com.doran.utils.exception.dto.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Auth {

    public static UserInfo getInfo() {
        Object temp = SecurityContextHolder.getContext()
                                           .getAuthentication()
                                           .getPrincipal();
        if (temp.getClass().equals(String.class)) {
            log.info("찾을 수 없는 유저 : {}", temp);
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        return (UserInfo)SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();

    }
}
