package acum.exam.services;

import acum.exam.ResourceNotFoundExceptionException;
import acum.exam.dto.StudentDto;
import acum.exam.dto.StudentWithCoursesDto;
import acum.exam.entities.CourseEntity;
import acum.exam.entities.CourseInstanceEntity;
import acum.exam.entities.LectureWithHoursEntity;
import acum.exam.entities.StudentEntity;
import acum.exam.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StudentsService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseInstanceRepository courseInstanceRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private StudentWithCourseCountRepository studentWithCourseCountRepository;

    public StudentWithCoursesDto getStudentWithCourses(long studentId) throws ResourceNotFoundExceptionException {

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundExceptionException());

        StudentWithCoursesDto result = StudentWithCoursesDto.builder()
                .fullName(studentEntity.getFirstName() + " " + studentEntity.getLastName())
                .email(studentEntity.getEmail())
                .courses(new ArrayList<>())
                .build();

        for (CourseInstanceEntity courseInstanceEntity : courseInstanceRepository.findByStudentId(studentId)) {

            CourseEntity courseEntity = courseRepository.findById(courseInstanceEntity.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Internal integrity problem, missing course with id " + courseInstanceEntity.getCourseId()));

            StudentWithCoursesDto.Course course = StudentWithCoursesDto.Course.builder()
                    .id(courseEntity.getId())
                    .name(courseEntity.getName())
                    .description(courseEntity.getDescription()).hours(courseEntity.getHours())
                    .startDate(courseInstanceEntity.getStartDate())
                    .lectures(new ArrayList<>())
                    .build();

            result.getCourses().add(course);

            List<LectureWithHoursEntity> lectureEntities = lectureRepository.findByCourseInstance(courseInstanceEntity.getId());
            for (LectureWithHoursEntity lectureEntity : lectureEntities) {

                course.getLectures().add(StudentWithCoursesDto.Lecture.builder()
                        .title(lectureEntity.getTitle())
                        .fieldOfStudy(lectureEntity.getFieldOfStudy())
                        .startTime(lectureEntity.getStartTime())
                        .endTime(lectureEntity.getEndTime())
                        .build());
            }

        }

        return result;
    }

    public List<StudentDto> getStudentsWithCoursesCount(int offset, int count) {

        return studentWithCourseCountRepository.findAllWithCount(offset, count).stream()
                .map(e -> StudentDto.builder()
                        .id(e.getId())
                        .fullName(e.getFirstName() + " " + e.getLastName())
                        .email(e.getEmail())
                        .courses(e.getCourses())
                        .build())
                .toList();
    }
}
