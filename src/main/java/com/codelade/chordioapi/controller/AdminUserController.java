package com.codelade.chordioapi.controller;

import com.codelade.chordioapi.dto.user.AdminUserCreateRequest;
import com.codelade.chordioapi.dto.user.AdminUserResponseDto;
import com.codelade.chordioapi.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @PostMapping
    public AdminUserResponseDto createUser(@RequestBody AdminUserCreateRequest request) {
        return adminUserService.createUser(request);
    }

    @GetMapping("/listUsers")
    public Page<AdminUserResponseDto> listUsers(Pageable pageable) {
        return adminUserService.listUsers(pageable);
    }

    @GetMapping("/{id}")
    public AdminUserResponseDto getUser(@PathVariable Long id) {
        return adminUserService.getUser(id);
    }

    @PutMapping("/{id}")
    public AdminUserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody AdminUserCreateRequest request
    ) {
        return adminUserService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminUserService.deleteUser(id);
    }

    @GetMapping("/search")
    public Page<AdminUserResponseDto> searchUsers(
            @RequestParam(required = false, name = "search") String search,
            Pageable pageable
    ) {
        return adminUserService.searchUsers(search, pageable);
    }
}
