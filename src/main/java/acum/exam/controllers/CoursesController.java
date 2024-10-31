package acum.exam.controllers;

import acum.exam.ValidationException;
import acum.exam.dto.CourseDto;
import acum.exam.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping(value = "/courses")
    public @ResponseBody List<CourseDto> getAllCourses(@RequestParam int offset, @RequestParam int count) throws ValidationException {

        if (count > 10)
            throw new ValidationException("Count must not exceed " + 10);

        return coursesService.findAllWithStudentsCount(offset, count);

    }
}
