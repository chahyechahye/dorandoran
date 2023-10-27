package com.doran.profile.dto.res;

import com.doran.animal.dto.res.AnimalDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
	private int id;
	private AnimalDto animal;
	private String name;
}
