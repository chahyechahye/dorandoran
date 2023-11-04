package com.doran.record_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doran.record_book.entity.RecordBook;

public interface RecordBookRepository extends JpaRepository<RecordBook, Integer>, RecordBookRepositoryCustom {
}
