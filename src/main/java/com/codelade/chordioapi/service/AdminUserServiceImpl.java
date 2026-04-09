package com.codelade.chordioapi.service;

import com.codelade.chordioapi.dto.user.AdminUserCreateRequest;
import com.codelade.chordioapi.dto.user.AdminUserResponseDto;
import com.codelade.chordioapi.entity.UserEntity;
import com.codelade.chordioapi.exception.EmailAlreadyExistsException;
import com.codelade.chordioapi.exception.UserNotFoundException;
import com.codelade.chordioapi.mapper.UserMapper;
import com.codelade.chordioapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminUserResponseDto createUser(AdminUserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public Page<AdminUserResponseDto> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    @Override
    public AdminUserResponseDto getUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toResponse(user);
    }

    @Override
    public AdminUserResponseDto updateUser(Long id, AdminUserCreateRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (!user.getEmail().equals(request.getEmail()) &&
                userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user.setRole(request.getRole());

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public Page<AdminUserResponseDto> searchUsers(String search, Pageable pageable) {
        if (search == null) search = "";

        Long id = null;
        try {
            id = Long.parseLong(search);
        } catch (NumberFormatException ignored) {}

        return userRepository
                .findByIdIsOrEmailContainingIgnoreCaseOrUserNameContainingIgnoreCase(
                        id, search, search, pageable
                )
                .map(userMapper::toResponse);
    }
}
