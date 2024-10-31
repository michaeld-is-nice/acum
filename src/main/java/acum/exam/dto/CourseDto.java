package acum.exam.dto;

import acum.exam.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseDto {

    private String name;
    private String description;
    private Integer hours;
    private Integer studentsCount;
}
