package com.codelade.chordioapi.service;

import com.codelade.chordioapi.dto.auth.AuthResponseDto;
import com.codelade.chordioapi.dto.auth.LoginRequestDto;
import com.codelade.chordioapi.dto.auth.RegisterRequestDto;

public interface AuthService {
    AuthResponseDto register(RegisterRequestDto request);

    AuthResponseDto login(LoginRequestDto request);
}
