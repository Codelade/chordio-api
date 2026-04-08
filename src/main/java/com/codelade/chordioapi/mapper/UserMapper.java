package com.codelade.chordioapi.mapper;

import com.codelade.chordioapi.dto.user.AdminUserResponseDto;
import com.codelade.chordioapi.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AdminUserResponseDto toResponse(UserEntity entity) {
        if (entity == null) return null;
        AdminUserResponseDto dto = new AdminUserResponseDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setUserName(entity.getUserName());
        dto.setRole(entity.getRole().name());
        return dto;
    }
}