package Rudiko.schoollanguages.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public Module(String title, String description, Long id){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Module() {

    }
}
