package com.example.demo.service;

import com.example.demo.data.StudentRepository;
import com.example.demo.data.StudentTakenPraticeRepository;
import com.example.demo.data.TeacherPracticeRepository;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentTakenPractice;
import com.example.demo.entity.TeacherPractice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTakenPracticeService {
    private final StudentTakenPraticeRepository studentTakenPraticeRepository;
    private final StudentRepository studentRepository;
    private final TeacherPracticeRepository teacherPracticeRepository;

    @Autowired

    public StudentTakenPracticeService(StudentTakenPraticeRepository studentTakenPraticeRepository,
                                       StudentRepository studentRepository, TeacherPracticeRepository teacherPracticeRepository) {
        super();
        this.studentTakenPraticeRepository = studentTakenPraticeRepository;
        this.studentRepository = studentRepository;
        this.teacherPracticeRepository = teacherPracticeRepository;
    }


    @Transactional
    public void saveStudentTakenPractice(StudentTakenPractice studentTakenPractice) {

        Student student = studentRepository.getStudentByUsername(studentTakenPractice.getStudent().getUser().getUsername());
        TeacherPractice teacherPractice = teacherPracticeRepository.getTeacherPracticeById(studentTakenPractice.getTeacherpractice().getTeacherpracticeid());

        // Set managed entities
        studentTakenPractice.setStudent(student);
        studentTakenPractice.setTeacherpractice(teacherPractice);

        // Save the entity
        studentTakenPraticeRepository.save(studentTakenPractice);
    }
}