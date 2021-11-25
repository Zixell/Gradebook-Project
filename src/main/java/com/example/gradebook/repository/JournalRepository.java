package com.example.gradebook.repository;

import com.example.gradebook.domain.Journal;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal, Long> {
    Iterable<Journal> findAllByStudentId(Long studentId);
}