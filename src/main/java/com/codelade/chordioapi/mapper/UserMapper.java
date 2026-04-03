package com.codelade.chordioapi.mapper;

import com.codelade.chordioapi.dto.user.DebugUserResponseDto;
import com.codelade.chordioapi.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public DebugUserResponseDto toDebug(UserEntity entity) {
        if (entity == null) return null;
        DebugUserResponseDto dto = new DebugUserResponseDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setUserName(entity.getUserName());
        dto.setRole(entity.getRole());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}