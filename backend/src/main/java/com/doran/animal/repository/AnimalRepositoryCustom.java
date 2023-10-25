package com.doran.animal.repository;

import java.util.Optional;

import com.doran.animal.dto.res.AnimalListDto;

public interface AnimalRepositoryCustom {

	Optional<AnimalListDto> selectAllAnimal();

}
