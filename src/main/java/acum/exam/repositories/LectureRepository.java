package acum.exam.repositories;

import acum.exam.entities.LectureWithHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureRepository  extends JpaRepository<LectureWithHoursEntity, Long> {

    @Query(nativeQuery = true, value = "" +
            "select  " +
            " l.*, cilh.start_time, cilh.end_time " +
            "from  " +
            " lectures l " +
            "left join course_instance_lecture_hours cilh " +
            " on cilh.lecture_id = l.id " +
            "where  " +
            " cilh.course_instance_id = ?")
    List<LectureWithHoursEntity> findByCourseInstance(Long courseInstanceId);
}
