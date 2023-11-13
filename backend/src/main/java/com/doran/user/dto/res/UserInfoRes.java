package com.doran.user.dto.res;

import com.doran.user.type.Roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRes {
    private int id;
    private String name;
    private Roles userRole;
}
