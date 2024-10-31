package acum.exam.services.impl;

import acum.exam.ValidationException;
import acum.exam.dto.CourseAssignment;
import acum.exam.entities.CourseInstanceEntity;
import acum.exam.entities.StudentEntity;
import acum.exam.repositories.*;
import acum.exam.services.StudentsService;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentsServiceImplTest {


    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseInstanceRepository courseInstanceRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private LectureRepository lectureRepository;
    @Mock
    private StudentWithCourseCountRepository studentWithCourseCountRepository;
    @Mock
    private CourseInstanceStudentRepository courseInstanceStudentRepository;

    @InjectMocks
    private StudentsServiceImpl studentsService;

    @Test
    void assignStudentToCourse_MissingCourses() {

        when(courseInstanceRepository.findAllById(any()))
                .thenReturn(List.of(new CourseInstanceEntity(), new CourseInstanceEntity()));

        when(studentRepository.findById(any()))
                .thenReturn(Optional.of(new StudentEntity()));

        CourseAssignment assignment = new CourseAssignment();
        assignment.setCourseInstances(List.of(1L));

        assertThrows(ValidationException.class,
                () -> studentsService.assignStudentToCourse(1L, assignment));
    }

    @Test
    void assignStudentToCourse_missingStudent() {

        when(studentRepository.findById(any()))
                .thenReturn(Optional.empty());

        CourseAssignment assignment = new CourseAssignment();
        assignment.setCourseInstances(List.of(1L));

        assertThrows(ValidationException.class,
                () -> studentsService.assignStudentToCourse(1L, assignment));
    }
}