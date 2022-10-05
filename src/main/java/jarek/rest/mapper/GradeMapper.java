package jarek.rest.mapper;

import jarek.rest.model.Grade;
import jarek.rest.model.dto.CreateGradeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    Grade createGradeFromDTO(CreateGradeDTO dto);
}
