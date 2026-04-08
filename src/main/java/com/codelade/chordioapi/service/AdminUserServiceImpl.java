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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public AdminUserResponseDto createUser(AdminUserCreateRequest request) {
        validateEmailAvailable(request.getEmail());

        UserEntity entity = new UserEntity();
        entity.setEmail(request.getEmail());
        entity.setUserName(request.getUserName());
        entity.setRole(request.getRole());
        entity.setPassword(request.getPassword());

        return userMapper.toResponse(userRepository.save(entity));
    }

    @Override
    public Page<AdminUserResponseDto> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public AdminUserResponseDto getUser(Long id) {
        return userMapper.toResponse(findUserOrThrow(id));
    }

    @Override
    public AdminUserResponseDto updateUser(Long id, AdminUserCreateRequest request) {
        UserEntity entity = findUserOrThrow(id);

        if (!entity.getEmail().equals(request.getEmail())) {
            validateEmailAvailable(request.getEmail());
        }

        entity.setEmail(request.getEmail());
        entity.setUserName(request.getUserName());
        entity.setRole(request.getRole());

        return userMapper.toResponse(userRepository.save(entity));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(findUserOrThrow(id));
    }

    @Override
    public Page<AdminUserResponseDto> searchUsers(String search, Pageable pageable) {
        String term = normalizeSearch(search);
        Long id = parseId(term);

        return userRepository
                .findByIdIsOrEmailContainingIgnoreCaseOrUserNameContainingIgnoreCase(
                        id, term, term, pageable
                )
                .map(userMapper::toResponse);
    }

    private UserEntity findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private void validateEmailAvailable(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    private String normalizeSearch(String search) {
        return (search == null || search.isBlank()) ? "" : search.trim();
    }

    private Long parseId(String term) {
        return term.matches("\\d+") ? Long.valueOf(term) : null;
    }
}
