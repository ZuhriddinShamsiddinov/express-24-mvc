package uz.jl.dto.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


import javax.validation.constraints.Size;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 8:04 AM 8/4/22 on Thursday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserCreateDTO {
    @NotEmpty(message = "not.empty.username")
    @Size(min = 2, max = 25, message = "valid.size")
    private String username;
    @NotEmpty(message = "not.empty.email")
    @Email(message = "valid.email")
    private String email;
    @NotEmpty(message = "not.empty.password")
    private String password;
    @NotEmpty(message = "not.empty.confirm.password")
    private String confirmPassword;
}
