package com.doran.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.page.entity.Page;

public interface PageRepository extends JpaRepository<Page, Integer>, PageRepositoryCustom {
}
