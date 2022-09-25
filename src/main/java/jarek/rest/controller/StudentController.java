package jarek.rest.controller;

import jarek.rest.model.Student;
import jarek.rest.srvice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
