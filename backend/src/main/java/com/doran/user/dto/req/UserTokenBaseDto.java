package com.doran.user.dto.req;

import com.doran.parent.type.Provider;
import com.doran.user.type.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTokenBaseDto {
	private int userId;
	private Integer parentId;
	private Integer childId;
	private Integer selectProfileId;
	private Provider provider;
	private Roles userRole;
}
