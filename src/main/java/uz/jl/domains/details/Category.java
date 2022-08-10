package uz.jl.domains.details;

import lombok.*;
import uz.jl.domains.Uploads;

import javax.persistence.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:41 AM 8/4/22 on Thursday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Uploads image;
}
