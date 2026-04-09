package com.codelade.chordioapi.dto.user;

import com.codelade.chordioapi.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserResponseDto {

    private Long id;
    private String email;
    private String userName;
    private Role role;

}