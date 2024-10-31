package acum.exam.controllers;

import acum.exam.ResourceNotFoundExceptionException;
import acum.exam.ValidationException;
import acum.exam.dto.*;
import acum.exam.services.StudentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping(value = "/student/{studentId}")
    public @ResponseBody StudentWithCoursesDto getStudent(@PathVariable long studentId) throws ResourceNotFoundExceptionException {

        return studentsService.getStudentWithCourses(studentId);

    }

    @GetMapping(value = "/students")
    public @ResponseBody List<StudentWithCoursesCountDto> getAllStudents(@RequestParam int offset, @RequestParam int count) throws ValidationException {

        if (count > 10)
            throw new ValidationException("Count must not exceed " + 10);

        return studentsService.getStudentsWithCoursesCount(offset, count);

    }

    @PutMapping(value = "/student")
    public @ResponseBody BaseDto createStudent(@Valid @RequestBody StudentDto studentDto) {

        return BaseDto.builder()
                .id(studentsService.addStudent(studentDto))
                .build();
    }

    @PatchMapping(value = "/student/{studentId}")
    public @ResponseBody void updateStudent(@RequestBody StudentDto studentDto, @PathVariable long studentId) throws ValidationException {

        studentsService.updateStudent(studentId, studentDto);
    }

    @PatchMapping(value = "/student/{studentId}/assign")
    public @ResponseBody StudentWithCoursesDto assignStudentToCourse(@RequestBody @Valid CourseAssignment assignment, @PathVariable long studentId) throws ValidationException, ResourceNotFoundExceptionException {

        studentsService.assignStudentToCourse(studentId, assignment);

        return studentsService.getStudentWithCourses(studentId);
    }
}
