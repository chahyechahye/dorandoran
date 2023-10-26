package com.doran.animal.repository;

import java.util.Optional;

import com.doran.animal.dto.res.AnimalListDto;
import com.doran.animal.entity.Animal;

public interface AnimalRepositoryCustom {

	Optional<Animal> selectAnimal(int id);

	Optional<AnimalListDto> selectAllAnimal();

}
