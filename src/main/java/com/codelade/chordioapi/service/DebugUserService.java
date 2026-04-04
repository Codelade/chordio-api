package com.codelade.chordioapi.service;

import com.codelade.chordioapi.dto.user.DebugUserCreateRequest;
import com.codelade.chordioapi.dto.user.DebugUserResponseDto;

import java.util.List;

public interface DebugUserService {

    DebugUserResponseDto createUser(DebugUserCreateRequest request);

    List<DebugUserResponseDto> getUsers();
}