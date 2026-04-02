package com.codelade.chordioapi.dto.user;

import com.codelade.chordioapi.security.Role;
import lombok.Data;

@Data
public class DebugUserCreateRequest {
    private String email;
    private String userName;
    private String password;
    private Role role;
}