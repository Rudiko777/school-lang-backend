package Rudiko.schoollanguages.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SupportApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "description")
    private String description;
}
