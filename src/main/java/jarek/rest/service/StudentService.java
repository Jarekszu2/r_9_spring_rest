package jarek.rest.service;

import jarek.rest.exception.WrongOperation;
import jarek.rest.mapper.GradeMapper;
import jarek.rest.mapper.StudentMapper;
import jarek.rest.model.Grade;
import jarek.rest.model.Student;
import jarek.rest.model.dto.AddGradeToStudent;
import jarek.rest.model.dto.AssignGradeToStudent;
import jarek.rest.model.dto.CreateStudentRequest;
import jarek.rest.model.dto.StudentUpdateRequest;
import jarek.rest.repository.GradeRepository;
import jarek.rest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private GradeRepository gradeRepository;

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

        /*pobierz oceny, nastepnie ustaw w studencie i zapisz nowego do bazy*/

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

    public Page<Student> getPage(PageRequest of) {
        return studentRepository.findAll(of);
    }

    public Long addGradeToStudent(AddGradeToStudent dto) {
        Optional<Student> optionalStudent = studentRepository.findById(dto.getStudentId());
        if (optionalStudent.isPresent()) {

            Student student = optionalStudent.get();

            Grade grade = gradeMapper.createGradeFromDto(dto);

            grade.setStudent(student);

            return gradeRepository.save(grade).getId();
        }
        throw new EntityNotFoundException("student, id: " + dto.getStudentId());
    }

    public Long assignGradeToStudent(AssignGradeToStudent dto) {

        Optional<Student> studentOptional = studentRepository.findById(dto.getStudentId());
        if (!studentOptional.isPresent()) {
            throw new EntityNotFoundException("student, id: " + dto.getStudentId());
        }

        Optional<Grade> optionalGrade = gradeRepository.findById(dto.getGradeId());
        if (!optionalGrade.isPresent()) {
            throw new EntityNotFoundException("grade, id: " + dto.getGradeId());
        }

        Student student = studentOptional.get();
        Grade grade = optionalGrade.get();

        if (grade.getStudent() != null) {
            throw new WrongOperation("You should not assign grade that is already assigned.");
        }

        grade.setStudent(student);

        return gradeRepository.save(grade).getId();
    }
}
