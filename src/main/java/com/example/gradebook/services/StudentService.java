package com.example.gradebook.services;

import com.example.gradebook.domain.Journal;
import com.example.gradebook.domain.Student;
import com.example.gradebook.repository.JournalRepository;
import com.example.gradebook.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final JournalRepository journalRepository;
    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public Optional<Student> getOne(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student studentFromDb, Student student) {
        BeanUtils.copyProperties(student, studentFromDb,"id");
        return studentRepository.save(studentFromDb);
    }

    @Transactional
    public void delete(Student student) {
        for (Journal journalRepo : journalRepository.findAllByStudentId(student.getId())) {
            journalRepository.delete(journalRepo);
        }
        studentRepository.delete(student);
    }
}