package acum.exam.repositories;

import acum.exam.entities.StudentEntity;
import acum.exam.entities.StudentWithCoursesCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
