package com.example.gradebook.services;

import com.example.gradebook.domain.Journal;
import com.example.gradebook.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;

    public List<Journal> findAll() {
        return (List<Journal>) journalRepository.findAll();
    }

    public Optional<Journal> getOne(Long id) {
        return journalRepository.findById(id);
    }

    public Iterable<Journal> listByStudentId(Long studentId) {
        return journalRepository.findAllByStudentId(studentId);
    }

    public Journal create(Journal journal) {
        return journalRepository.save(journal);
    }

    public Journal update(Journal journalFromDb, Journal journal) {
        BeanUtils.copyProperties(journal, journalFromDb,"id");
        return journalRepository.save(journalFromDb);
    }

    public void delete(Journal journal) {
        journalRepository.delete(journal);
    }
}