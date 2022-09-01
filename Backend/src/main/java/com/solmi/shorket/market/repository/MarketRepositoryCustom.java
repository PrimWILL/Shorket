package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.MarketFilteringCriteriaByDate;
import com.solmi.shorket.market.dto.MarketSortingCriteria;

import java.util.List;

public interface MarketRepositoryCustom {

    /**
     * 정렬 기준과 필터링 기준, 페이지 번호를 전달받아 Market List 조회
     *
     * @param sort
     * @param date
     * @param locals
     * @param page
     * @return 전달받은 기준으로 조회된 Market List return
     */
    List<Market> findMarkets(MarketSortingCriteria sort, MarketFilteringCriteriaByDate date,
                             List<String> locals, Integer page);
}
