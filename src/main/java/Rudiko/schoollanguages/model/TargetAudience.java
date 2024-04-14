package Rudiko.schoollanguages.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "audience")
public class TargetAudience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "whom")
    private String whom;
    @Column(name = "description")
    private String description;

    @Column(name = "image", columnDefinition = "text")
    private String image;
}
