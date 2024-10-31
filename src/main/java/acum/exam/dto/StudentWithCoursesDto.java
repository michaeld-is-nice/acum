package acum.exam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class StudentWithCoursesDto {

    private long id;
    private String fullName;
    private String email;
    private List<Course> courses;

    @Getter
    @Setter
    @Builder
    public static class Course {
        private Long id;
        private String name;
        private String description;
        private Integer hours;
        @JsonFormat(pattern="yyyy/MM/dd")
        private Date startDate;
        private List<Lecture> lectures;
    }

    @Getter
    @Setter
    @Builder
    public static class Lecture {
        private String title;
        private String fieldOfStudy;
        @JsonFormat(pattern="yyyy/MM/dd HH:mm")
        private Date startTime;
        @JsonFormat(pattern="yyyy/MM/dd HH:mm")
        private Date endTime;
    }
}
