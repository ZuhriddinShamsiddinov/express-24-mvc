package uz.jl.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.domains.auth.AuthUser;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String username);
}
