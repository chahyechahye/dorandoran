package com.doran.page.repository;

import java.util.Optional;

import com.doran.page.entity.Page;

public interface PageRepositoryCustom {
    Optional<Page> findPageByBookIdAndIdx(int bookId, int idx);
}
