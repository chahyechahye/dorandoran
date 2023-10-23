package com.doran.user.dto.req;

import com.doran.user.type.Roles;

import lombok.Getter;

@Getter
public class UserJoinDto {
	private String name;
	private Roles userRole;
}
