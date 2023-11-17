package com.doran.animal.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.doran.animal.dto.res.AnimalDto;
import com.doran.animal.dto.res.AnimalListDto;

@Mapper(componentModel = "Spring")
public interface AnimalMapper {

	AnimalDto toAnimalDto(int id, String name, String imgUrl);

	default AnimalListDto toAnimalListDto(List<AnimalDto> animalListDto) {
		AnimalListDto animalListDto1 = new AnimalListDto();
		animalListDto1.setAnimalList(animalListDto);
		return animalListDto1;
	}
}
