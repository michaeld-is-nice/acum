package acum.exam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "lectures")
public class LectureWithHoursEntity extends BaseEntity {

    private Long courseId;
    private String title;
    private String fieldOfStudy;
    private Timestamp startTime;
    private Timestamp endTime;
}
