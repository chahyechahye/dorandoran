package com.doran.letter.repository;

import java.util.List;

import com.doran.letter.entity.Letter;

public interface LetterRepositoryCustom {
    List<Letter> findAllLetterByParentId(int parentId);
    Letter findLetterByParentId(int parentId);
    List<Letter> findAllLetterByProfileId(int profileId);
    Letter findLetterByProfileId(int profileId);
}
