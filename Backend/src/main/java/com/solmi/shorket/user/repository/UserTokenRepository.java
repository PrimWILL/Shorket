package com.solmi.shorket.user.repository;

import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.domain.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {

    Optional<UserToken> findByUserIdx(Integer userIdx);
}
