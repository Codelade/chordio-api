package com.codelade.chordioapi.mapper;

import com.codelade.chordioapi.dto.user.AdminUserResponseDto;
import com.codelade.chordioapi.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AdminUserResponseDto toResponse(UserEntity user) {
        AdminUserResponseDto dto = new AdminUserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUserName(user.getUserName());
        dto.setRole(user.getRole());
        return dto;
    }
}
