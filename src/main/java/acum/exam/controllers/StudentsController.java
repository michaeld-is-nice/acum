package acum.exam.controllers;

import acum.exam.ResourceNotFoundExceptionException;
import acum.exam.ValidationException;
import acum.exam.dto.StudentDto;
import acum.exam.dto.StudentWithCoursesDto;
import acum.exam.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody List<StudentDto> getAllStudents(@RequestParam int offset, @RequestParam int count) throws ValidationException {

        if (count > 10)
            throw new ValidationException("Count must not exceed " + 10);

        return studentsService.getStudentsWithCoursesCount(offset, count);

    }
}
