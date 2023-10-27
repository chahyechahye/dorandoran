package com.doran.utils.auth;

import org.springframework.security.core.context.SecurityContextHolder;

import com.doran.utils.common.UserInfo;

public class Auth {

    public static UserInfo getInfo() {
        return (UserInfo)SecurityContextHolder.getContext()
                                              .getAuthentication()
                                              .getPrincipal();

    }

}
