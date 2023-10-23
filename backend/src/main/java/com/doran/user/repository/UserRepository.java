package com.doran.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
