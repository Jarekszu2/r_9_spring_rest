package jarek.rest.model.dto;

import jarek.rest.model.GradeSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGradeWithGradeSubject {

    private Integer id;
    private GradeSubject gradeSubject;
}
