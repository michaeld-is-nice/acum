package acum.exam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "course_instances")
public class CourseInstanceEntity extends BaseEntity {

    private Long courseId;
    private Timestamp startDate;
}
