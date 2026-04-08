package com.codelade.chordioapi.service;

import com.codelade.chordioapi.dto.user.AdminUserCreateRequest;
import com.codelade.chordioapi.dto.user.AdminUserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminUserService {

    Page<AdminUserResponseDto> listUsers(Pageable pageable);
    Page<AdminUserResponseDto> searchUsers(String search, Pageable pageable);
    AdminUserResponseDto createUser(AdminUserCreateRequest request);
    AdminUserResponseDto getUser(Long id);
    AdminUserResponseDto updateUser(Long id, AdminUserCreateRequest request);
    void deleteUser(Long id);


}