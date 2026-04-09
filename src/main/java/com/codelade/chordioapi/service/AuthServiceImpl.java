package com.codelade.chordioapi.service;

import com.codelade.chordioapi.dto.auth.AuthResponseDto;
import com.codelade.chordioapi.dto.auth.LoginRequestDto;
import com.codelade.chordioapi.dto.auth.RegisterRequestDto;
import com.codelade.chordioapi.entity.UserEntity;
import com.codelade.chordioapi.exception.EmailAlreadyExistsException;
import com.codelade.chordioapi.repository.UserRepository;
import com.codelade.chordioapi.security.JwtService;
import com.codelade.chordioapi.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponseDto(
                token,
                user.getId(),
                user.getEmail(),
                user.getUserName(),
                user.getRole()
        );
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        UserEntity user = userRepository
                .findByEmailOrUserName(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = jwtService.generateToken(user);

        return new AuthResponseDto(
                token,
                user.getId(),
                user.getEmail(),
                user.getUserName(),
                user.getRole()
        );
    }

}
