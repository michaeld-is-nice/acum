package acum.exam.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseAssignment {

    @NotNull
    @NotEmpty
    private List<Long> courseInstances;
}
