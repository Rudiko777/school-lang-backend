package Rudiko.schoollanguages.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class BestStudents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "score")
    private Long score;
    
    @ManyToMany(mappedBy = "bestStudents")
    private List<Module> modules;

    public BestStudents(String fullName, Long score, List<Module> updatedModules) {
        this.fullName = fullName;
        this.score = score;
        this.modules = updatedModules;
    }
}
