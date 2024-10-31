package acum.exam.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentDto {

    private long id;
    private String fullName;
    private String email;
    private Integer courses;
}
