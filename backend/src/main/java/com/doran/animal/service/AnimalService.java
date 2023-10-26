package com.doran.animal.service;

import com.doran.animal.dto.res.AnimalDto;
import com.doran.animal.dto.res.AnimalListDto;

public interface AnimalService {

	AnimalDto selectAnimal(int id);

	AnimalListDto selectAllAnimal();
}
