package acum.exam.services;

import acum.exam.dto.CourseDto;

import java.util.List;

public interface CoursesService {
    List<CourseDto> findAllWithStudentsCount(int offset, int count);
}
