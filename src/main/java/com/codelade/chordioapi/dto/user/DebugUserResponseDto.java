package com.codelade.chordioapi.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DebugUserResponseDto extends AdminUserResponseDto {
    private String password;
}
