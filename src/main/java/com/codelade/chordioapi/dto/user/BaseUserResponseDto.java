package com.codelade.chordioapi.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserResponseDto {
    private Long id;
    private String email;
    private String userName;
}