package com.solmi.shorket.user.repository;

import com.solmi.shorket.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Id로 유저 조회
    Optional<User> findById(Integer idx);

    /**
     * Email로 User 조회
     *
     * @param email 조회할 User의 email
     * @return 조회한 User 객체
     */
    Optional<User> findByEmail(String email);

    /**
     * Email에 해당하는 User가 존재하는지 여부 확인
     *
     * @param email 존재 여부를 확인할 User의 email
     * @return 존재 여부
     */
    boolean existsByEmail(String email);
}
