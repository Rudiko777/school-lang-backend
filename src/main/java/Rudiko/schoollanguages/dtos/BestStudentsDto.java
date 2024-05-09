package Rudiko.schoollanguages.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestStudentsDto {
    private String fullName;
    private Long score;
    private Long idActiveModule;
    private Long courseId;
}
