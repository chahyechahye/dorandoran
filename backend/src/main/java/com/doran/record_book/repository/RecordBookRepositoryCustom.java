package com.doran.record_book.repository;

import java.util.List;

import com.querydsl.core.Tuple;

public interface RecordBookRepositoryCustom {

    List<Tuple> findToTalPage();
}
