package jarek.rest.controller;

import jarek.rest.model.Grade;
import jarek.rest.model.GradeSubject;
import jarek.rest.model.Student;
import jarek.rest.model.dto.CreateGradeDTO;
import jarek.rest.model.dto.CreateGradeWithGradeSubject;
import jarek.rest.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public List<Grade> getGradeList() {
        return gradeService.findAll();
    }

    @PutMapping
    public Long putGrade(CreateGradeDTO dto) {
        return gradeService.putGrade(dto);
    }

    @GetMapping("/bySubject")
    public List<Grade> getGradeListBySubject(GradeSubject subject) {
        return gradeService.findAllBySubject(subject);
    }

    @GetMapping("/byStudentId") // szukam np. nie przypisanych Grade'ów; w interfejsie w wyszukiwaniu po studentId nic nie wpisuję
    public List<Grade> getGradeListByStudentId(Long studentId) {
        return gradeService.findAllByStudent(studentId);
    }
}
