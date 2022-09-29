package jarek.rest.controller;

import jarek.rest.model.Student;
import jarek.rest.model.dto.StudentUpdateRequest;
import jarek.rest.srvice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.CompositeData;
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
    public Long putStudent(Student student) {
        // id
        return studentService.save(student);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void postStudent(StudentUpdateRequest student) {
        studentService.update(student);
    }
}
