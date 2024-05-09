package Rudiko.schoollanguages.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Module> modules;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;
}