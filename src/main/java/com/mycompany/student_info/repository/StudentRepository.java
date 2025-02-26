package com.mycompany.student_info.repository;
import com.mycompany.student_info.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}