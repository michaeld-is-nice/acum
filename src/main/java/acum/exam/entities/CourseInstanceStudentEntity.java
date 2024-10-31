package acum.exam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course_instance_students")
public class CourseInstanceStudentEntity extends BaseEntity {

    private Long courseInstanceId;
    private Long studentId;
}
