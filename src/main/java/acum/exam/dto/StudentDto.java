package acum.exam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class StudentDto {

    @Length(min = 3, max = 50)
    @NotBlank(message = "Firstname is mandatory")
    private String firstName;
    @Length(min = 3, max = 50)
    @NotBlank(message = "Firstname is mandatory")
    private String lastName;
    @Email(message = "Email is mandatory")
    private String email;
}
