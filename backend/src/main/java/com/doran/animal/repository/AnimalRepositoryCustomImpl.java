package com.doran.animal.repository;

import static com.doran.animal.entity.QAnimal.*;

import java.util.List;
import java.util.Optional;

import com.doran.animal.dto.res.AnimalDto;
import com.doran.animal.dto.res.AnimalListDto;
import com.doran.animal.entity.Animal;
import com.doran.animal.mapper.AnimalMapper;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnimalRepositoryCustomImpl implements AnimalRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;
	private final AnimalMapper animalMapper;

	@Override
	public Optional<Animal> selectAnimal(int id) {
		return Optional.ofNullable(
			jpaQueryFactory.select(animal)
						   .from(animal)
						   .where(animal.id.eq(id))
						   .fetchOne());
	}

	@Override
	public Optional<AnimalListDto> selectAllAnimal() {
		List<AnimalDto> animalList = jpaQueryFactory.select(Projections.fields(
														AnimalDto.class,
														animal.id,
														animal.name,
														animal.imgUrl)).from(animal)
													.fetch();
		return Optional.ofNullable(animalMapper.toAnimalListDto(animalList));
	}
}
