package jarek.rest.controller;

import jarek.rest.model.Grade;
import jarek.rest.model.dto.CreateGradeDTO;
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
}
