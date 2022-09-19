package com.solmi.shorket.user.repository;

import com.solmi.shorket.user.domain.ExpiredAccessToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExpiredAccessTokenRedisRepository extends CrudRepository<ExpiredAccessToken, String> {

    Optional<ExpiredAccessToken> findByUserIdx(Integer userIdx);
}
