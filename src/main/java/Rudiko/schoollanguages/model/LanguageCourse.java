package Rudiko.schoollanguages.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    public LanguageCourse(Long id, String language, String level) {
        this.id = id;
        this.language = language;
        this.level = level;
    }

    public LanguageCourse() {

    }
}