package uz.jl.domains.auth;

import lombok.*;
import uz.jl.domains.auth.AuthRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class AuthUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    @ManyToMany(targetEntity = AuthRole.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_role_id", referencedColumnName = "id")
    )
    private List<AuthRole> roles = new ArrayList<>();

}
