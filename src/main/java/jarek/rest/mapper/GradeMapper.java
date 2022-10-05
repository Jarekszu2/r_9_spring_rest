package jarek.rest.mapper;

import jarek.rest.model.Grade;
import jarek.rest.model.dto.AddGradeToStudent;
import jarek.rest.model.dto.CreateGradeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    Grade createGradeFromDTO(CreateGradeDTO dto);

    @Mappings(value = {
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "subject", target = "subject"),
            @Mapping(target = "id", ignore = true)
    })
    Grade createGradeFromDto(AddGradeToStudent dto);
}
