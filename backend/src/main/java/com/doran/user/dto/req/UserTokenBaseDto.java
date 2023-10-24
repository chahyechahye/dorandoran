package com.doran.user.dto.req;

import com.doran.parent.type.Provider;
import com.doran.user.type.Roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenBaseDto {
	private int userId;
	private int parentId;
	private int childId;
	private int selectProfileId;
	private Provider provider;
	private Roles userRole;
}
