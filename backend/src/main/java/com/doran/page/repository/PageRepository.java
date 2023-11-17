package com.doran.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.page.entity.Page;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Integer>, PageRepositoryCustom {
    List<Page> findPagesByBookId(int bookId);
}
