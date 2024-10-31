package acum.exam.repositories;

import acum.exam.entities.CourseWithStudentsCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseWithStudentsCountRepository extends JpaRepository<CourseWithStudentsCountEntity, Long> {

    @Query(nativeQuery = true, value =
            "select   " +
            "  c.*, students.count as students_count  " +
            "from   " +
            "  courses c   " +
            "left join (   " +
            "  select course_id, count(cis.student_id) as count from   " +
            "    course_instances ci   " +
            "  left join course_instance_students cis on   " +
            "    cis.course_instance_id = ci.id   " +
            "  group by ci.course_id) students on students.course_id = c.id limit ?,?")
    List<CourseWithStudentsCountEntity> findAllWithStudentCount(int offset, int limit);
}
