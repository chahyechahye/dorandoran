package com.doran.page.repository;

import java.util.List;
import java.util.Optional;

import com.doran.page.dto.res.PageDetailDto;
import com.doran.page.entity.Page;
import com.doran.utils.common.Genders;

public interface PageRepositoryCustom {
    Optional<Page> findPageByBookIdAndIdx(int bookId, int idx);
    List<PageDetailDto> findPageDetailByUserIdAndBookId(int userId, int bookId, Genders genders);
}
