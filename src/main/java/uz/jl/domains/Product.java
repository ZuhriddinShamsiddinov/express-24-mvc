package uz.jl.domains;

import lombok.*;

import javax.persistence.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:44 AM 8/4/22 on Thursday in August
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @OneToOne
    private Uploads image;

    @ManyToOne
    private Category category;
}
