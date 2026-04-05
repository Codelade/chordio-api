package com.codelade.chordioapi.service;

import com.codelade.chordioapi.dto.user.DebugUserCreateRequest;
import com.codelade.chordioapi.dto.user.DebugUserResponseDto;
import com.codelade.chordioapi.entity.UserEntity;
import com.codelade.chordioapi.mapper.UserMapper;
import com.codelade.chordioapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebugUserServiceImpl implements DebugUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public DebugUserResponseDto createUser(DebugUserCreateRequest request) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setPassword(request.getPassword());
        return userMapper.toDebug(userRepository.save(entity));
    }

    @Override
    public List<DebugUserResponseDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(DebugUserResponseDto::new)
                .toList();
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }


}
