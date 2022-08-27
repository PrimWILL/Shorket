package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer>, MarketRepositoryCustom {
}