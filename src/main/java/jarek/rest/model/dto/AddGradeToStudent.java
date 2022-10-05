package jarek.rest.model.dto;

import jarek.rest.model.GradeSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// podajemy id studenta i wszystkie niezbędne parametry do utworzenia nowej oceny
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGradeToStudent {

    private Long studentId;
    private double value;
    private GradeSubject subject;
}
