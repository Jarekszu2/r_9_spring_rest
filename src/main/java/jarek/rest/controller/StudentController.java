package jarek.rest.controller;

import jarek.rest.model.Student;
import jarek.rest.srvice.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.openmbean.CompositeData;
import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private StudentService studentService;

    @GetMapping
    public List<Student> getStudentList() {
        return studentService.getAll();
    }
}
