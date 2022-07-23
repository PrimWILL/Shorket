package com.solmi.shorket.user.repository;

import com.solmi.shorket.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Id로 유저 조회
    Optional<User> findById(Long idx);

    // email로 유저 조회
    Optional<User> findByEmail(String email);
}
