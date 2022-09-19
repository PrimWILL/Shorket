package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.MarketFilteringCriteriaByDate;
import com.solmi.shorket.market.dto.MarketSortingCriteria;
import com.solmi.shorket.user.domain.User;

import java.util.List;

public interface MarketRepositoryCustom {

    /**
     * 정렬 기준과 필터링 기준, 페이지 번호를 전달받아 Market List 조회
     *
     * @param sort   정렬 기준 - VIEW(조회순), INTEREST(관심순), LATEST(최신순), DICT(사전순)
     * @param date   필터링 기준(날짜) - UPCOMING(예정), CURRENT(진행 중), COMPLETE(종료)
     * @param locals 필터링 기준(지역, 시/도) - 서울, 경기, ...
     * @param page   조회할 page 번호
     * @return 전달받은 기준으로 조회된 Market List
     * @throws IllegalArgumentException 정렬, 필터링 기준으로 잘못된 값이 전달되었을 경우
     */
    List<Market> findMarkets(MarketSortingCriteria sort, MarketFilteringCriteriaByDate date,
                             List<String> locals, Integer page);

    /**
     * 로그인 사용자가 관리자인 Market List 조회
     *
     * @param manager Market 관리자(로그인 사용자)
     * @param sort    정렬 기준 - VIEW(조회순), INTEREST(관심순), LATEST(최신순), DICT(사전순)
     * @param page    조회할 page 번호
     * @return 전달받은 기준으로 조회된 Managed Market List
     * @throws IllegalArgumentException 정렬, 필터링 기준으로 잘못된 값이 전달되었을 경우
     */
    List<Market> findManagedMarkets(User manager, MarketSortingCriteria sort, Integer page);
}
