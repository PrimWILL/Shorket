package com.solmi.shorket.user.repository;

import com.solmi.shorket.user.domain.LogoutAccessToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LogoutAccessTokenRedisRepository extends CrudRepository<LogoutAccessToken, Integer> {

    Optional<LogoutAccessToken> findByUserIdx(Integer userIdx);
}
