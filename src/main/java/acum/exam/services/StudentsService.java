package acum.exam.services;

import acum.exam.ResourceNotFoundExceptionException;
import acum.exam.ValidationException;
import acum.exam.dto.CourseAssignment;
import acum.exam.dto.StudentDto;
import acum.exam.dto.StudentWithCoursesCountDto;
import acum.exam.dto.StudentWithCoursesDto;

import java.util.List;

public interface StudentsService {

    StudentWithCoursesDto getStudentWithCourses(long studentId) throws ResourceNotFoundExceptionException;
    List<StudentWithCoursesCountDto> getStudentsWithCoursesCount(int offset, int count);
    Long addStudent(StudentDto studentDto);
    void updateStudent(long studentId, StudentDto studentDto) throws ValidationException;
    void assignStudentToCourse(long studentId, CourseAssignment assignment) throws ValidationException;
}
