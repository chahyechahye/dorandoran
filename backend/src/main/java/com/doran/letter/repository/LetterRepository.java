package com.doran.letter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doran.letter.entity.Letter;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Integer>, LetterRepositoryCustom {
}
