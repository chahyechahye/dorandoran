package com.doran.animal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.animal.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer>, AnimalRepositoryCustom {
}
