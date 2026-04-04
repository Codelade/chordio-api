package com.codelade.chordioapi.dto.user;

import com.codelade.chordioapi.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebugUserResponseDto {

    private Long id;
    private String email;
    private String userName;
    private String password;
    private String role;

    public DebugUserResponseDto(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.role = user.getRole().name();
    }
}