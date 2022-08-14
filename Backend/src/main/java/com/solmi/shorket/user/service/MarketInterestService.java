package com.solmi.shorket.user.service;

import com.solmi.shorket.global.exception.DuplicateMarketInterestException;
import com.solmi.shorket.global.exception.MarketInterestNotFoundException;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.MarketInterest;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.repository.MarketInterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MarketInterestService {
    private final MarketInterestRepository marketInterestRepository;

    /**
     * 관심 마켓 추가
     */
    @Transactional
    public void addInterest(User user, Market market) {
        validateAlreadyInterest(user, market);

        MarketInterest marketInterest = MarketInterest.createMarketInterest(user, market);
        marketInterestRepository.save(marketInterest);
    }

    /**
     * 관심 마켓 취소
     */
    @Transactional
    public void cancelInterest(User user, Market market) {
        MarketInterest marketInterest = marketInterestRepository.findByUserAndMarket(user, market).orElseThrow(MarketInterestNotFoundException::new);
        market.getInterests().remove(marketInterest);
        marketInterestRepository.delete(marketInterest);
    }

    private void validateAlreadyInterest(User user, Market market) {
        if (marketInterestRepository.existsByUserAndMarket(user, market)) {
            throw new DuplicateMarketInterestException();
        }
    }
}
