package com.solmi.shorket.market.service;

import com.solmi.shorket.global.exception.MarketNotFoundException;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.UpdateMarketDto;
import com.solmi.shorket.market.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    public Page<Market> findMarkets(Pageable pageable) {
        return marketRepository.findAllByStartDateBeforeAndEndDateAfter(pageable, LocalDateTime.now(), LocalDateTime.now());
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
    public void updateMarket(Integer marketId, UpdateMarketDto marketDto) {
        Market market = marketRepository.findById(marketId)
                .orElseThrow(MarketNotFoundException::new);
        market.update(marketDto);
    }
}
