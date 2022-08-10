package uz.jl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import uz.jl.configs.security.UserDetails;
import uz.jl.domains.AuthUser;
import uz.jl.dto.AuthUserCreateDTO;
import uz.jl.exceptions.ObjectAlreadyExistsException;
import uz.jl.repository.AuthRepository;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return new UserDetails(authUser);
    }

    public void save(AuthUserCreateDTO userCreateDTO, BindingResult bindingResult) {
        Optional<AuthUser> userOptional = authRepository.findByUsername(userCreateDTO.getUsername());
        if (userOptional.isPresent()) {
            throw new ObjectAlreadyExistsException("User already exists");
        }
        if (!Objects.equals(userCreateDTO.getPassword(), userCreateDTO.getConfirmPassword())) {
            bindingResult.addError(new FieldError("userCreateDTO", "confirmPassword", "Confirm Password not equal password"));
            return;
        }

        AuthUser authUser = AuthUser
                .builder()
                .username(userCreateDTO.getUsername())
                .password(passwordEncoder.encode(userCreateDTO.getPassword()))
                .build();
        authRepository.save(authUser);
    }
}
