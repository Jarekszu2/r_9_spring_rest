package jarek.rest.srvice;

import jarek.rest.mapper.StudentMapper;
import jarek.rest.model.Student;
import jarek.rest.model.dto.CreateStudentRequest;
import jarek.rest.model.dto.StudentUpdateRequest;
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

    @Autowired
    private StudentMapper studentMapper;


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

    public Long save(CreateStudentRequest dto) {

        Student student = studentMapper.createStudentFromDTO(dto);

        return studentRepository.save(student).getId();
    }

    public void update(StudentUpdateRequest studentToEdit) {
        Optional<Student> optionalStudent = studentRepository.findById(studentToEdit.getStudentId());
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
            if (studentToEdit.getIsAlive() != null) {
                student.setAlive(studentToEdit.getIsAlive());
            }

            studentRepository.save(student);
            return;

        }
        throw new EntityNotFoundException("student, id: " + studentToEdit.getStudentId());

    }

    public void delete(Long deletedId) {
        if (studentRepository.existsById(deletedId)) {
            studentRepository.deleteById(deletedId);
        }
        throw new EntityNotFoundException("student, id: " + deletedId);
    }
}
