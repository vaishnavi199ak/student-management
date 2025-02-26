import com.mycompany.student_info.domain.Student
import com.mycompany.student_info.repository.StudentRepository
import com.mycompany.student_info.service.StudentInfoService
import spock.lang.Specification

public class StudentInfoServiceTest extends Specification {
    StudentRepository studentRepository = Mock()
    StudentInfoService studentInfoService = new StudentInfoService(studentRepository)

    def "get all students success"() {
        given:
        def student1 = Student.builder().id(1).name("John").age(23).course("BSC").build()
        def student2 = Student.builder().id(2).name("Doe").age(24).course("BSC").build()
        def students = [student1, student2]

        and:
        studentRepository.findAll() >> students

        when:
        def response = studentInfoService.getAllStudents()

        then:
        response.get(0).name == students.get(0).name
        response.get(1).name == students.get(1).name
        noExceptionThrown()
    }

    def "get student by id success"() {
        given:
        def student = Student.builder().id(1).name("John").age(23).course("BSC").build()

        and:
        studentRepository.findById(1) >> student

        when:
        def response = studentInfoService.getStudentById(1)

        then:
        response.name == student.name
        noExceptionThrown()
    }

    def "get student by id failure"() {
        when:
        def response = studentInfoService.getStudentById(1)

        then:   
        def exception = thrown(StudentNotFoundException)
        exception.message == "Student not found"
    }

    def "add student success"() {
        given:
        def student = Student.builder().id(1).name("John").age(23).course("BSC").build()

        when:
        def response = studentInfoService.addStudent(student)

        then:
        response.name == student.name
        noExceptionThrown()
    }

    def "update student success"() {
        given:
        def student = Student.builder().id(1).name("John").age(23).course("BSC").build()

        when:
        def response = studentInfoService.updateStudent(student)

        then:
        response.name == student.name
        noExceptionThrown()
    }

    def "update student failure"() {
        when:
        def response = studentInfoService.updateStudent(null)

        then:   
        def exception = thrown(StudentNotFoundException)
        exception.message == "Student not found"
    }

    def "delete student success"() {
        when:
        def response = studentInfoService.deleteStudent(1)

        and:
        studentRepository.delete(student) >> students

        then:
        response == "Student deleted successfully"
        noExceptionThrown()
    }

    def "delete student failure"() {
        when:
        def response = studentInfoService.deleteStudent(1)

        then:   
        def exception = thrown(StudentNotFoundException)
        exception.message == "Student not found"
    }
}