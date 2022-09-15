package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.MarketFilteringCriteriaByDate;
import com.solmi.shorket.market.dto.MarketSortingCriteria;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class MarketRepositoryCustomImpl implements MarketRepositoryCustom {

    private final EntityManager em;

    private final static int NUMBER_OF_PAGING = 10;

    @Override
    public List<Market> findMarkets(MarketSortingCriteria sort, MarketFilteringCriteriaByDate date,
                                    List<String> locals, Integer page) {
        // Filtering
        String filteringQuery = "where ";

        // 기간 filtering
        switch (date) {
            case UPCOMING:
                filteringQuery += "m.startDate > :now ";
                break;
            case CURRENT:
                filteringQuery += "m.startDate < :now and m.endDate > :now ";
                break;
            case COMPLETE:
                filteringQuery += "m.endDate < :now ";
                break;
            default:
                throw new IllegalArgumentException("잘못된 형식의 기간입니다.");
        }

        // 지역 filtering
        filteringQuery += "and m.address.sido in :locals ";

        // Sorting
        String sortingQuery = "order by ";

        switch (sort) {
            case INTEREST:
                sortingQuery += "m.marketInterestCount desc";
                break;
            case VIEW:
                sortingQuery += "m.viewCount desc";
                break;
            case LATEST:
                sortingQuery += "m.createdAt desc";
                break;
            case DICT:
                sortingQuery += "m.name";
                break;
            default:
                throw new IllegalArgumentException("잘못된 형식의 정렬 기준입니다.");
        }

        return em.createQuery("select m from Market m " + filteringQuery + sortingQuery, Market.class)
                .setParameter("now", LocalDateTime.now())
                .setParameter("locals", locals)
                .setFirstResult(page * NUMBER_OF_PAGING)
                .setMaxResults(NUMBER_OF_PAGING)
                .getResultList();
    }
}
