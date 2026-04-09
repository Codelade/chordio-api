package com.codelade.chordioapi.controller;

import com.codelade.chordioapi.dto.auth.AuthResponseDto;
import com.codelade.chordioapi.dto.auth.LoginRequestDto;
import com.codelade.chordioapi.dto.auth.RegisterRequestDto;
import com.codelade.chordioapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterRequestDto request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }
}
