package uz.jl.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.domains.auth.AuthRole;

public interface AuthPermissionRepository extends JpaRepository<AuthRole, Long> {
}
