package Rudiko.schoollanguages.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LanguageCourse {
    private String language;
    private String title;
    private String level;
    private int duration;
    private int quantityModules;
    private int price;
}