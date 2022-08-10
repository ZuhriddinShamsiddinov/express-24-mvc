package uz.jl.domains;

import lombok.*;

import javax.persistence.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:13 PM 8/7/22 on Sunday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "basket_item")
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(targetEntity = AuthUser.class, fetch = FetchType.EAGER)
    private AuthUser authUser;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    private Product product;

    @Builder.Default
    @Column(name = "is_confirmed")
    private boolean isConfirmed = false;
}
