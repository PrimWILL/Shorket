package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.SortingAndFilteringInfo;

import java.util.List;

public interface MarketRepositoryCustom {

    List<Market> findMarkets(SortingAndFilteringInfo info);
}
