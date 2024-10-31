package acum.exam.services.impl;

import acum.exam.dto.CourseDto;
import acum.exam.repositories.CourseWithStudentsCountRepository;
import acum.exam.services.CoursesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CourseWithStudentsCountRepository courseWithStudentsCountRepository;

    public List<CourseDto> findAllWithStudentsCount(int offset, int count) {

        return courseWithStudentsCountRepository.findAllWithStudentCount(offset, count).stream()
                .map(e -> CourseDto.builder()
                        .name(e.getName())
                        .description(e.getDescription())
                        .hours(e.getHours())
                        .studentsCount(e.getStudentsCount())
                        .build())
                .toList();
    }

}
