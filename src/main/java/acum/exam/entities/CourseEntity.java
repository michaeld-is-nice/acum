package acum.exam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class CourseEntity extends BaseEntity {

    private String name;
    private String description;
    private Integer hours;
}
