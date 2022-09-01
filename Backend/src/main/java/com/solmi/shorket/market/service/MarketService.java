package com.solmi.shorket.market.service;

import com.solmi.shorket.global.exception.MarketNotFoundException;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.MarketFilteringCriteriaByDate;
import com.solmi.shorket.market.dto.MarketSortingCriteria;
import com.solmi.shorket.market.dto.UpdateMarketRequestDto;
import com.solmi.shorket.market.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;

    /**
     * Market 등록
     */
    @Transactional
    public Market saveMarket(Market market) {
        return marketRepository.save(market);
    }

    /**
     * Market 목록 조회 - 정렬 기준 적용
     */
    public List<Market> findMarkets(MarketSortingCriteria sort, MarketFilteringCriteriaByDate date,
                                    List<String> locals, Integer page) {
        return marketRepository.findMarkets(sort, date, locals, page);
    }

    /**
     * Market 조회
     */
    public Market findMarket(Integer marketId) {
        Market market = marketRepository.findById(marketId)
                .orElseThrow(MarketNotFoundException::new);
        market.addViewCount();  // 조회수 증가
        return market;
    }

    /**
     * Market 수정
     */
    @Transactional
    public void updateMarket(Integer marketId, UpdateMarketRequestDto updateDto) {
        Market market = marketRepository.findById(marketId)
                .orElseThrow(MarketNotFoundException::new);
        market.update(updateDto);
    }

    /**
     * Market 삭제
     */
    @Transactional
    public void removeMarket(Integer marketId) {
        Market market = marketRepository.findById(marketId)
                .orElseThrow(MarketNotFoundException::new);
        marketRepository.delete(market);
    }
}
