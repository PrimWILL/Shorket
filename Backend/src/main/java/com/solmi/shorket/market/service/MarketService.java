package com.solmi.shorket.market.service;

import com.solmi.shorket.global.exception.MarketNotFoundException;
import com.solmi.shorket.global.exception.MarketUnauthorizedException;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.domain.MarketImage;
import com.solmi.shorket.market.dto.MarketFilteringCriteriaByDate;
import com.solmi.shorket.market.dto.MarketSortingCriteria;
import com.solmi.shorket.market.dto.UpdateMarketRequestDto;
import com.solmi.shorket.market.repository.MarketImageRepository;
import com.solmi.shorket.market.repository.MarketRepository;
import com.solmi.shorket.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
    private final MarketImageRepository marketImageRepository;

    /**
     * 새로운 Market을 등록한다.
     *
     * @param market 등록할 Market 객체
     * @return 등록된 Market 객체
     */
    @Transactional
    public Market saveMarket(Market market) {
        return marketRepository.save(market);
    }

    /**
     * Market 조회
     * 조회 시 조회수가 증가하므로 사용에 주의해야 함.
     *
     * @param marketIdx 조회할 Market의 idx
     * @return 조회된 Market 객체
     * @throws MarketNotFoundException 조회할 Market이 없는 경우
     */
    public Market findMarket(Integer marketIdx) {
        Market market = marketRepository.findById(marketIdx)
                .orElseThrow(MarketNotFoundException::new);
        market.addViewCount();  // 조회수 증가
        return market;
    }

    /**
     * 정렬 기준과 필터링 기준, 페이지 번호를 전달받아 Market List 조회
     *
     * @param sort   정렬 기준 - VIEW(조회순), INTEREST(관심순), LATEST(최신순), DICT(사전순)
     * @param date   필터링 기준(날짜) - UPCOMING(예정), CURRENT(진행 중), COMPLETE(종료)
     * @param locals 필터링 기준(지역, 시/도) - 서울, 경기, ...
     * @param page   조회할 page 번호
     * @return 조회된 Market List
     * @throws IllegalArgumentException 정렬, 필터링 기준으로 잘못된 값이 전달되었을 경우
     */
    public List<Market> findMarkets(MarketSortingCriteria sort, MarketFilteringCriteriaByDate date,
                                    List<String> locals, Integer page) {
        return marketRepository.findMarkets(sort, date, locals, page);
    }

    /**
     * user가 관리하는 Market 목록 조회
     *
     * @param user manager
     * @param sort 정렬 기준
     * @param page page number
     * @return 조회된 Market List
     */
    public List<Market> findManagedMarkets(User user, MarketSortingCriteria sort, Integer page) {
        return marketRepository.findManagedMarkets(user, sort, page);
    }

    /**
     * 관심 마켓 목록 조회
     *
     * @param user User
     * @return 조회된 관심 마켓 목록
     */
    public List<Market> getInterestMarkets(Pageable pageable, User user) {
        return marketRepository.findMarketsByInterestsIn(pageable, user.getMarketInterests());
    }

    /**
     * Market 수정
     *
     * @param marketIdx 수정할 Market의 idx
     * @param updateDto 수정할 내용
     * @throws MarketNotFoundException     수정할 Market이 없는 경우
     * @throws MarketUnauthorizedException Market 수정 권한이 없는 경우
     */
    @Transactional
    public void updateMarket(User user, Integer marketIdx, UpdateMarketRequestDto updateDto) {
        Market market = marketRepository.findById(marketIdx)
                .orElseThrow(MarketNotFoundException::new);

        validateMarketAuthority(user, market);

        List<MarketImage> removeImages = new ArrayList<>(market.getImages());
        market.getImages().clear();
        marketImageRepository.deleteAll(removeImages);

        market.update(
                updateDto.getName(), updateDto.getDescription(), updateDto.getAddress(),
                updateDto.getOpenTime(), updateDto.getCloseTime(),
                updateDto.getStartDate(), updateDto.getEndDate(),
                updateDto.getImageUrls(), updateDto.getMapImageUrl()
        );
    }

    /**
     * Market 삭제
     *
     * @param marketIdx 삭제할 Market의 idx
     * @throws MarketNotFoundException     삭제할 Market이 없는 경우
     * @throws MarketUnauthorizedException Market 삭제 권한이 없는 경우
     */
    @Transactional
    public void deleteMarket(User user, Integer marketIdx) {
        Market market = marketRepository.findById(marketIdx)
                .orElseThrow(MarketNotFoundException::new);

        validateMarketAuthority(user, market);

        marketRepository.delete(market);
    }

    /**
     * Market 수정/삭제 권한 검증. Market 관리자에게만 권한이 부여된다.
     *
     * @param user   수정/삭제를 하려는 User
     * @param market 수정/삭제를 하려는 Market
     * @throws MarketUnauthorizedException Market 수정/삭제 권한이 없는 경우
     */
    private void validateMarketAuthority(User user, Market market) {
        if (!user.getIdx().equals(market.getManager().getIdx())) {
            throw new MarketUnauthorizedException();
        }
    }
}
