package uz.jl.domains.details;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:41 PM 8/8/22 on Monday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Timestamp expiryDate;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String password;
}
