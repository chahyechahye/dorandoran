package com.doran.parent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.parent.entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

}
