package uz.jl.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.domains.auth.AuthRole;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
    Optional<AuthRole> findByNameEqualsIgnoreCase(String name);
}
