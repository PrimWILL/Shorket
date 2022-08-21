package com.solmi.shorket.user.repository;

import com.solmi.shorket.user.domain.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {

    Optional<UserToken> findByUserIdx(Integer userIdx);

    List<UserToken> findAllByUserIdx(Integer userIdx);

    Long deleteAllByUserIdx(Integer userIdx);
}
