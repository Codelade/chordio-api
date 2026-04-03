package com.codelade.chordioapi.controller;

import com.codelade.chordioapi.dto.user.DebugUserCreateRequest;
import com.codelade.chordioapi.dto.user.DebugUserResponseDto;
import com.codelade.chordioapi.service.DebugUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/debug")
@RequiredArgsConstructor
public class DebugUserController {

    private final DebugUserService debugUserService;

    // Create user with any role without hiding password DEV ONLY
    @PostMapping("/users")
    public DebugUserResponseDto createUser(@RequestBody DebugUserCreateRequest request) {
        return debugUserService.createUser(request);
    }

}