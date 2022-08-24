package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.SortingAndFilteringInfo;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.solmi.shorket.market.dto.MarketFilteringCriteriaByDate.*;
import static com.solmi.shorket.market.dto.MarketSortingCriteria.*;

@RequiredArgsConstructor
public class MarketRepositoryCustomImpl implements MarketRepositoryCustom {

    private final EntityManager em;

    private final int NUMBER_OF_PAGING = 10;

    @Override
    public List<Market> findMarkets(SortingAndFilteringInfo info) {
        // Sorting
        String joinQuery = "";
        String sortingQuery = "order by ";

        // 정렬 기준이 관심 많은 순인 경우 join을 해야하기 때문에 따로 query 생성
        if (info.getSort() == INTEREST) {
            joinQuery += "join MarketInterest mi on mi.market=m ";
            sortingQuery = "group by m order by count(m) desc";
        } else if (info.getSort() == VIEW) {
            sortingQuery += "m.viewCount desc";
        } else if (info.getSort() == LATEST) {
            sortingQuery += "m.createdAt desc";
        } else if (info.getSort() == DICT) {
            sortingQuery += "m.name";
        } else {
            throw new IllegalArgumentException("Bad Request");
        }

        // Filtering
        String filteringQuery = "where ";

        // 기간 filtering
        if (info.getDate() == UPCOMING) {
            filteringQuery += "m.startDate > :now ";
        } else if (info.getDate() == CURRENT) {
            filteringQuery += "m.startDate < :now and m.endDate > :now ";
        } else if (info.getDate() == COMPLETE) {
            filteringQuery += "m.endDate < :now ";
        } else {
            throw new IllegalArgumentException("Bad Request");
        }

        // 지역 filtering
        if (!info.getLocals().isEmpty()) {
            filteringQuery += "and m.address.sido in (";
            for (String local : info.getLocals()) {
                filteringQuery += "'" + local + "', ";
            }
            filteringQuery += "'') ";
        }

        return em.createQuery("select m from Market m " + joinQuery + filteringQuery + sortingQuery)
                .setParameter("now", LocalDateTime.now())
                .setFirstResult(0)
                .setMaxResults(NUMBER_OF_PAGING)
                .getResultList();
    }
}
