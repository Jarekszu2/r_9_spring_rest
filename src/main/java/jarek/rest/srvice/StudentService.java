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

    public Long save(Student student) {
        return studentRepository.save(student).getId();
    }

    public void update(Student studentToEdit) {
        Optional<Student> optionalStudent = studentRepository.findById(studentToEdit.getId());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            if (studentToEdit.getName() != null) {
                student.setName(studentToEdit.getName());
            }
            if (studentToEdit.getSurname() != null) {
                student.setSurname(studentToEdit.getSurname());
            }
            if (studentToEdit.getDateOfBirth() != null) {
                student.setDateOfBirth(studentToEdit.getDateOfBirth());
            }
//            if (studentToEdit.getIsAlive() != null) {
//                student.setAlive(studentToEdit.getIsAlive());
//            }

            studentRepository.save(student);
            return;

        }
        throw new EntityNotFoundException("student, id: " + studentToEdit.getId());

    }
}
