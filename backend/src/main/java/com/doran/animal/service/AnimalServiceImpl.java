package com.doran.animal.service;

import org.springframework.stereotype.Service;

import com.doran.animal.dto.res.AnimalDto;
import com.doran.animal.dto.res.AnimalListDto;
import com.doran.animal.entity.Animal;
import com.doran.animal.mapper.AnimalMapper;
import com.doran.animal.repository.AnimalRepository;
import com.doran.exception.dto.CustomException;
import com.doran.exception.dto.ErrorCode;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AnimalServiceImpl implements AnimalService {

	private final AnimalMapper animalMapper;
	private final AnimalRepository animalRepository;

	@Override
	public AnimalDto selectAnimal(int id) {
		Animal animal = animalRepository.selectAnimal(id)
										.orElseThrow(() -> new CustomException(ErrorCode.ANIMAL_NOT_FOUND));
		return animalMapper.toAnimalDto(animal.getId(), animal.getName(), animal.getImgUrl());
	}

	@Override
	public AnimalListDto selectAllAnimal() {
		return animalRepository.selectAllAnimal().orElseThrow(() -> new CustomException(ErrorCode.ANIMAL_NOT_FOUND));
	}
}
