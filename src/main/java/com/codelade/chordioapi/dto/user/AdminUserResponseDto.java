package com.codelade.chordioapi.dto.user;

import com.codelade.chordioapi.security.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdminUserResponseDto extends BaseUserResponseDto {
    private Role role;
}