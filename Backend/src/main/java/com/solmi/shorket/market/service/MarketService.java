package com.solmi.shorket.market.service;

import com.solmi.shorket.market.domain.Market;
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
     * Market 목록 조회 - 정렬 기준 적용
     */
    public Page<Market> findMarkets(Pageable pageable) {
        return marketRepository.findAllByStartDateBeforeAndEndDateAfter(pageable, LocalDateTime.now(), LocalDateTime.now());
    }

    /**
     * 조회수 증가
     */
    public void addViewCount(Integer marketId) {
        validateMarket(marketId);

        Market market = marketRepository.findById(marketId).get();
        market.addViewCount();
    }

    // 존재하지 않는 market인지 검증
    private void validateMarket(Integer marketId) {
        if (!marketRepository.existsById(marketId)) {
            throw new IllegalStateException("존재하지 않는 플리마켓입니다.");
        }
    }
}
