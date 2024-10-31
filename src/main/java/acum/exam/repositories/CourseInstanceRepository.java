package acum.exam.repositories;

import acum.exam.entities.CourseInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseInstanceRepository extends JpaRepository<CourseInstanceEntity, Long> {

    @Query(nativeQuery = true, value =
            "select " +
            "   ci.* " +
            "from " +
            "   course_instances ci " +
            "inner join course_instance_students cis on " +
            "   cis.course_instance_id = ci.id " +
            "where cis.student_id = ?")
    List<CourseInstanceEntity> findByStudentId(Long studentId);
}
