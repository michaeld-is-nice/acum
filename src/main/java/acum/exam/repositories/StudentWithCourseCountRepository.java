package acum.exam.repositories;

import acum.exam.entities.StudentWithCoursesCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentWithCourseCountRepository extends JpaRepository<StudentWithCoursesCountEntity, Long> {

    @Query(nativeQuery = true, value = "select s.*, coalesce(c.c, 0) as courses_count from students s left join (select count(*) c, student_id from course_instance_students group by student_id) c on c.student_id = s.id limit ?,?")
    List<StudentWithCoursesCountEntity> findAllWithCount(int offset, int limit);
}
