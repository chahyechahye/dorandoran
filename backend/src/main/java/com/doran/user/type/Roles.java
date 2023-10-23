package com.doran.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Roles {
	ADMIN("ROLE_ADMIN"),
	PARENT("ROLE_PARENT"),
	CHILD("ROLE_CHILD");

	private final String role;
}
