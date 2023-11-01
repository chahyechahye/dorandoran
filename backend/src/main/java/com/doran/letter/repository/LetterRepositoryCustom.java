package com.doran.letter.repository;

import java.util.List;
import java.util.Optional;

import com.doran.letter.entity.Letter;

public interface LetterRepositoryCustom {
    Optional<Letter> findLetterByUserId(int userId);
}
