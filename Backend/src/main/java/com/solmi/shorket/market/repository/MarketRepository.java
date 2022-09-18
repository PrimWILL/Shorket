package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market, Integer>, MarketRepositoryCustom {

    /**
     * @param idx must not be {@literal null}.
     * @return 조회된 Optional Market Object
     */
    @Override
    @EntityGraph(attributePaths = {"images", "mapImage"})
    Optional<Market> findById(Integer idx);
}