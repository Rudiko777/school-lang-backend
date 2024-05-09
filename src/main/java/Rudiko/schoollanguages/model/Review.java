package Rudiko.schoollanguages.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "title")
    private String title;

    @Column(name = "grade")
    private Long grade;

    @Column(name = "description")
    private String description;
}
