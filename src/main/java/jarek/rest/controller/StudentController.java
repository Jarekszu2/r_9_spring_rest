package jarek.rest.controller;

import jarek.rest.model.Student;
import jarek.rest.model.dto.AddGradeToStudent;
import jarek.rest.model.dto.AssignGradeToStudent;
import jarek.rest.model.dto.CreateStudentRequest;
import jarek.rest.model.dto.StudentUpdateRequest;
import jarek.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

//    C   R   U    D
//    PUT GET POST DELETE

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudentList() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long studentId) {
        return studentService.getById(studentId);
    }

    @PutMapping
    public Long putStudent(@RequestBody CreateStudentRequest dto) {
        // id
        return studentService.save(dto);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postStudent(@RequestBody StudentUpdateRequest student) {
        studentService.update(student);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable ("id") Long deletedId) {
        studentService.delete(deletedId);
    }

    @GetMapping("/getPage")
    public Page<Student> getPage(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "5") int size) {
        return studentService.getPage(PageRequest.of(page, size));
    }

    @PostMapping("/grade")
    public Long addGrade(@RequestBody AddGradeToStudent dto) {
        return studentService.addGradeToStudent(dto);
    }

    @PostMapping("/assignGrade")
    public Long addGrade(@RequestBody AssignGradeToStudent dto) {
        return studentService.assignGradeToStudent(dto);
    }
}
