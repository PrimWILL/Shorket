package com.solmi.shorket.user.service;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.repository.MarketRepository;
import com.solmi.shorket.user.domain.MarketInterest;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.repository.MarketInterestRepository;
import com.solmi.shorket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MarketInterestService {

    private final UserRepository userRepository;
    private final MarketRepository marketRepository;
    private final MarketInterestRepository marketInterestRepository;

    /**
     * 관심 마켓 추가
     */
    @Transactional
    public void addInterest(Integer userId, Integer marketId) {
        User user = userRepository.findById(userId).get();
        Market market = marketRepository.findById(marketId).get();

        validateAlreadyInterest(user, market);

        MarketInterest marketInterest = MarketInterest.createMarketInterest(user, market);
        marketInterestRepository.save(marketInterest);
    }

    /**
     * 관심 마켓 취소
     */
    @Transactional
    public void cancelInterest(Integer userId, Integer marketId) {
        User user = userRepository.findById(userId).get();
        Market market = marketRepository.findById(marketId).get();

        validateNotInterest(user, market);

        MarketInterest marketInterest = marketInterestRepository.findByUserAndMarket(user, market).get();
        market.getInterests().remove(marketInterest);
        marketInterestRepository.delete(marketInterest);
    }

    private void validateAlreadyInterest(User user, Market market) {
        if (marketInterestRepository.existsByUserAndMarket(user, market)) {
            throw new IllegalStateException("이미 관심 마켓으로 등록한 플리마켓입니다.");
        }
    }

    private void validateNotInterest(User user, Market market) {
        if (!marketInterestRepository.existsByUserAndMarket(user, market)) {
            throw new IllegalStateException("관심 마켓으로 등록한 적 없는 마켓입니다. 취소할 수 없습니다.");
        }
    }
}
