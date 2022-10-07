package jarek.rest.service;

import jarek.rest.mapper.GradeMapper;
import jarek.rest.model.Grade;
import jarek.rest.model.GradeSubject;
import jarek.rest.model.Student;
import jarek.rest.model.dto.CreateGradeDTO;
import jarek.rest.model.dto.CreateGradeWithGradeSubject;
import jarek.rest.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeMapper gradeMapper;

    public Long putGrade(CreateGradeDTO dto) {

        Grade grade = gradeMapper.createGradeFromDTO(dto);

        return gradeRepository.save(grade).getId();
    }

    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    public List<Grade> findAllBySubject(GradeSubject subject) {
        return gradeRepository.findAllBySubject(subject);
    }

    public List<Grade> findAllByStudent(Long studentId) {
        return gradeRepository.findAllByStudent_Id(studentId);
    }


//    public List<Grade> findAllBySubject(CreateGradeWithGradeSubject dto) {
//        Grade grade = gradeMapper.createGradeFromDTO(dto);
//        return gradeRepository.findAllBySubject(grade.getSubject());
//    }
}
