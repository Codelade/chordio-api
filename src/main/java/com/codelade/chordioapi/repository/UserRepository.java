package com.codelade.chordioapi.repository;

import com.codelade.chordioapi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmailOrUserName(String email, String userName);

    Page<UserEntity> findByIdIsOrEmailContainingIgnoreCaseOrUserNameContainingIgnoreCase(
            Long id,
            String email,
            String userName,
            Pageable pageable
    );
}
