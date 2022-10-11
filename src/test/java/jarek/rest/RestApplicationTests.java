package jarek.rest;

import jarek.rest.model.Student;
import jarek.rest.model.dto.CreateStudentRequest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class RestApplicationTests {

    @Test
    public void contextLoads() {
        Assert.assertTrue(true);
    }

        /*
    Klasa odpowiada za wywołanie zapytań REST.

    Adresuje zawsze adres naszego serwera (lokalhost) i jeśli zdefiniowany jest poprawny port
    w konfiguracji (np. DEFINED_PORT) to adresuje go na wybranych przez nas mappingach.

    Przykład:

    testRestTemplate.exchange("/student, HttpMethod.GET ...) adresuje "localhost:8080/student
     */

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String STUDENT_NAME = "Jarek";
    private final String STUDENT_SURNAME = "Zegarek";
    private final boolean STUDENT_ALIVE = true;
    private final LocalDate STUDENT_DATE = LocalDate.of(2000, 1, 1);

    @Test
    public void test_1_add_student() {
        CreateStudentRequest request = new CreateStudentRequest(null, STUDENT_NAME, STUDENT_SURNAME, STUDENT_ALIVE, STUDENT_DATE);

        HttpEntity<CreateStudentRequest> httpEntity = new HttpEntity<>(request);

        ResponseEntity<Long> responseEntity = testRestTemplate.exchange("/student", HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<Long>() {
        });

        Long createdId = responseEntity.getBody();

        Assert.assertNotNull(createdId);
    }

    @Test
    public void test_2_check_added_student() {
        ResponseEntity<List<Student>> responseEntity = testRestTemplate.exchange("/student", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        });

        List<Student> studentList = responseEntity.getBody();

        Assert.assertFalse(studentList.isEmpty());
        Assert.assertEquals(1, studentList.size());

        Student retrived = studentList.get(0);

        Assert.assertEquals(STUDENT_NAME, retrived.getName());
        Assert.assertEquals(STUDENT_SURNAME, retrived.getSurname());
        Assert.assertEquals(STUDENT_ALIVE, retrived.isAlive());
        Assert.assertEquals(STUDENT_DATE, retrived.getDateOfBirth());

        int ageSinceBirthYear = LocalDate.now().getYear() - STUDENT_DATE.getYear();

        Assert.assertEquals(ageSinceBirthYear, retrived.getAge());
    }
}
