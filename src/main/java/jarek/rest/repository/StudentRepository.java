package jarek.rest.repository;

import jarek.rest.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllBySurname(String surname, Pageable pageable);
    List<Student> findAllBySurname(String surname);
}
