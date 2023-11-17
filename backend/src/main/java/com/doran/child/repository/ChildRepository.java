package com.doran.child.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.child.entity.Child;

public interface ChildRepository extends JpaRepository<Child, Integer>, ChildRepositoryCustom {
}
