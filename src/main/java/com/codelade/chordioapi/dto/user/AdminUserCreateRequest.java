package com.codelade.chordioapi.dto.user;

import com.codelade.chordioapi.security.Role;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class AdminUserCreateRequest {

    @Email(message = "Invalid email format")
    @NotBlank(message ="Email is required")
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;
}