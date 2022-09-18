package com.solmi.shorket.booth.repository;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothInterest;
import com.solmi.shorket.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoothInterestRepository extends JpaRepository<BoothInterest, Integer> {

    Optional<BoothInterest> findByUserAndBooth(User user, Booth booth);

    boolean existsByUserAndBooth(User user, Booth booth);

    void deleteByUserAndBooth(User user, Booth booth);
}
