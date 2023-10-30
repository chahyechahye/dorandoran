package com.doran.letter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.letter.entity.Letter;

public interface LetterRepository extends JpaRepository<Letter, Integer>, LetterRepositoryCustom {
}
