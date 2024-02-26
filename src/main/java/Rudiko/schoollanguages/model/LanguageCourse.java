package Rudiko.schoollanguages.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class LanguageCourse {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    private String language;
    private String title;
    private String level;
    private int duration;
    private int quantityModules;
    private int price;
}