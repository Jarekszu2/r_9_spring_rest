package jarek.rest.srvice;

import jarek.rest.model.Student;
import jarek.rest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        throw new EntityNotFoundException("student, id: " + studentId);
    }
}
