package com.mycompany.student_info.service;
import com.mycompany.student_info.domain.Student;
import com.mycompany.student_info.repository.StudentRepository;
import com.mycompany.student_info.webclient.StudentWebclient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
public class StudentInfoService {
    private StudentRepository studentRepository;

    private StudentWebclient studentWebclient;

    public StudentInfoService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            return null;
        }
        existingStudent.setName(student.getName());
        return studentRepository.save(existingStudent);
    }

    public String getAttendance(){
        return studentWebclient.getStudentsAttendance(Long.valueOf(1));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}