package acum.exam.repositories;

import acum.exam.entities.CourseInstanceStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInstanceStudentRepository extends JpaRepository<CourseInstanceStudentEntity, Long> {

}
