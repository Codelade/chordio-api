package com.codelade.chordioapi.repository;

import com.codelade.chordioapi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    Page<UserEntity> findByIdIsOrEmailContainingIgnoreCaseOrUserNameContainingIgnoreCase(
            Long id,
            String email,
            String userName,
            Pageable pageable
    );


}