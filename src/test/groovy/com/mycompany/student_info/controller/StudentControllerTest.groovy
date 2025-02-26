package com.mycompany.student_info.controller

import com.mycompany.student_info.domain.Student
import com.mycompany.student_info.service.StudentInfoService
import spock.lang.Specification

class StudentControllerTest extends Specification {

StudentInfoService studentInfoService = Mock()
StudentController studentController = new StudentController(studentInfoService)

    def "get all students success"() {
        given:
        def student1 = Student.builder().id(1).name("John").age(23).course("BSC").build()
        def student2 = Student.builder().id(2).name("Doe").age(24).course("BSC").build()
        def students = [student1, student2]

        and:
        studentInfoService.getAllStudents() >> students

        when:
        def response = studentController.getAllStudents()

        then:
        response.get(0).name == students.get(0).name
        response.get(1).name == students.get(1).name
        noExceptionThrown()
    }

    def "get student by id success"() {
        given:
        def student = Student.builder().id(1).name("John").age(23).course("BSC").build()

        and:
        studentInfoService.getStudentById(1) >> student

        when:
        def response = studentController.getStudentById(1)

        then:
        response.name == student.name
        noExceptionThrown()
    }


    def "add student success"() {
        given:
        def student = Student.builder().id(1).name("John").age(23).course("BSC").build()

        and:
        studentInfoService.addStudent(student) >> student

        when:
        def response = studentController.addStudent(student)

        then:
        response.name == student.name
        noExceptionThrown()
    }   

    def "update student success"() {
        given:
        def student = Student.builder().id(1).name("John").age(23).course("BSC").build()

        and:
        studentInfoService.updateStudent(1, student) >> student

        when:
        def response = studentController.updateStudent(1,student)

        then:
        response.name == student.name
        noExceptionThrown()
    }

    def "delete student success"() {
        when:
        def response = studentController.deleteStudent(1)

        and:
        studentInfoService.deleteStudent(1)

        then:
        noExceptionThrown()
    }

}