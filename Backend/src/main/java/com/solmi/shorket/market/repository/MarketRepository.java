package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MarketRepository extends JpaRepository<Market, Integer> {

    /**
     * 진행중인 Markets 조회
     * @param pageable - Paging 정보, 정렬 기준
     * @param now1,2 - 현재 시각
     * Parameter 중복이 있으니 추후에 변경할 예정
     */
    Page<Market> findAllByStartDateBeforeAndEndDateAfter(Pageable pageable, LocalDateTime now1, LocalDateTime now2);
}