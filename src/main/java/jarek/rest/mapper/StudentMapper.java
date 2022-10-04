package jarek.rest.mapper;

import jarek.rest.model.Student;
import jarek.rest.model.dto.CreateStudentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student createStudentFromDTO(CreateStudentRequest dto);
}
