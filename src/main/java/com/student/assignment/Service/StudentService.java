package com.student.assignment.Service;

import com.student.assignment.Entities.Student;
import com.student.assignment.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student createStudent(Student s) {
        Student student = studentRepository.save(s);
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> list = (List<Student>) studentRepository.findAll();
        return list;
    }

    public Optional<Student> getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student;
    }

    public Student updateStudent(int id, Student updateStudent) {
        updateStudent.setId(id);
        Student updatedStudent = studentRepository.save(updateStudent);
        return updatedStudent;
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public void deleteAllStudent() {
        studentRepository.deleteAll();
    }
}
