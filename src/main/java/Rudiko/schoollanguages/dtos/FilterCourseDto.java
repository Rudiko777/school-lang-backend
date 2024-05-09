package Rudiko.schoollanguages.dtos;

import lombok.Data;

import java.util.List;

@Data
public class FilterCourseDto {
    private String language;
    private List<String> levels;
}
