package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.SortingAndFilteringInfo;

import java.util.List;

public interface MarketRepositoryCustom {

    /**
     * 정렬 기준과 필터링 기준, 페이지 번호를 전달받아 Market List 조회
     * @param info
     * @return 전달받은 기준으로 조회된 Market List return
     */
    List<Market> findMarkets(SortingAndFilteringInfo info, Integer page);
}
