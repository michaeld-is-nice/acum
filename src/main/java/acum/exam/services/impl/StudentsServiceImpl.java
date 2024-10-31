package acum.exam.services.impl;

import acum.exam.ResourceNotFoundExceptionException;
import acum.exam.ValidationException;
import acum.exam.dto.CourseAssignment;
import acum.exam.dto.StudentDto;
import acum.exam.dto.StudentWithCoursesCountDto;
import acum.exam.dto.StudentWithCoursesDto;
import acum.exam.entities.*;
import acum.exam.repositories.*;
import acum.exam.services.StudentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {

    private final StudentRepository studentRepository;
    private final CourseInstanceRepository courseInstanceRepository;
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final StudentWithCourseCountRepository studentWithCourseCountRepository;
    private final CourseInstanceStudentRepository courseInstanceStudentRepository;

    @Override
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

    @Override
    public List<StudentWithCoursesCountDto> getStudentsWithCoursesCount(int offset, int count) {

        return studentWithCourseCountRepository.findAllWithCount(offset, count).stream()
                .map(e -> StudentWithCoursesCountDto.builder()
                        .id(e.getId())
                        .fullName(e.getFirstName() + " " + e.getLastName())
                        .email(e.getEmail())
                        .courses(e.getCourses())
                        .build())
                .toList();
    }

    @Override
    public Long addStudent(StudentDto studentDto) {

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setLastName(studentDto.getLastName());
        studentEntity.setEmail(studentDto.getEmail());

        return studentRepository.save(studentEntity).getId();
    }

    @Override
    public void updateStudent(long studentId, StudentDto studentDto) throws ValidationException {

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ValidationException("missing student with id " + studentId));

        if (studentDto.getFirstName() != null)
            studentEntity.setFirstName(studentDto.getFirstName());
        if (studentDto.getLastName() != null)
            studentEntity.setLastName(studentDto.getLastName());
        if (studentDto.getEmail() != null)
            studentEntity.setEmail(studentDto.getEmail());

        studentRepository.save(studentEntity);
    }

    @Override
    public void assignStudentToCourse(long studentId, CourseAssignment assignment) throws ValidationException {

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ValidationException("missing student with id " + studentId));

        List<CourseInstanceEntity> coursesInstances = courseInstanceRepository.findAllById(assignment.getCourseInstances());

        if (coursesInstances.size() != assignment.getCourseInstances().size())
            throw new ValidationException("Not all requested courses are exist");

        List<CourseInstanceStudentEntity> entities = assignment.getCourseInstances().stream()
                .map(ciid -> {

                    CourseInstanceStudentEntity entity = new CourseInstanceStudentEntity();
                    entity.setStudentId(studentId);
                    entity.setCourseInstanceId(ciid);

                    return entity;
                })
                .toList();

        courseInstanceStudentRepository.saveAll(entities);
    }
}
