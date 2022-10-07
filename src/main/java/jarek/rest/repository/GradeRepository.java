package jarek.rest.repository;

import jarek.rest.model.Grade;
import jarek.rest.model.GradeSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllBySubject(GradeSubject subject);

    List<Grade> findAllByStudent_Id(Long studentId);
}

