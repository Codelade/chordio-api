package com.codelade.chordioapi.dto.user;

import lombok.Data;

@Data
public class AdminUserCreateRequest {
    private String email;
    private String userName;
    private String password;
}