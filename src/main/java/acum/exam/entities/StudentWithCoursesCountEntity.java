package acum.exam.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class StudentWithCoursesCountEntity extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "courses_count")
    private Integer courses;
}
