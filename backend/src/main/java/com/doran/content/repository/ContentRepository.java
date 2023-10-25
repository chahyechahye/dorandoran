package com.doran.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doran.content.entity.Content;

public interface ContentRepository extends JpaRepository<Content, Integer>,ContentRepositoryCustom{
}
