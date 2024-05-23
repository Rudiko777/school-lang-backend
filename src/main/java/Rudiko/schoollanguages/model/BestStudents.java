package Rudiko.schoollanguages.model;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "best_students_modules",
            joinColumns = @JoinColumn(name = "best_student_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id"))
    private List<Module> activeModules;

    public BestStudents(String fullName, Long score, List<Module> updatedModules) {
        this.fullName = fullName;
        this.score = score;
        this.activeModules = updatedModules;
    }
}
