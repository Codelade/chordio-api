package com.codelade.chordioapi.dto.auth;

import com.codelade.chordioapi.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {

    private String token;
    private Long id;
    private String email;
    private String userName;
    private Role role;
}
