package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.MarketInterest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market, Integer>, MarketRepositoryCustom {

    /**
     * @param idx must not be {@literal null}.
     * @return 조회된 Optional Market Object
     */
    @Override
    @EntityGraph(attributePaths = {"images", "mapImage"})
    Optional<Market> findById(Integer idx);

    @EntityGraph(attributePaths = {"images", "mapImage"})
    List<Market> findMarketsByInterestsIn(Pageable pageable, Collection<MarketInterest> interests);
}