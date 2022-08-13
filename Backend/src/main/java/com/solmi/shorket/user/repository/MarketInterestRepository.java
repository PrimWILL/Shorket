package com.solmi.shorket.user.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.MarketInterest;
import com.solmi.shorket.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketInterestRepository extends JpaRepository<MarketInterest, Integer> {

    Optional<MarketInterest> findByUserAndMarket(User user, Market market);

    boolean existsByUserAndMarket(User user, Market market);
}
