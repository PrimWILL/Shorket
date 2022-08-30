package com.solmi.shorket.market.repository;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.SortingAndFilteringInfo;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class MarketRepositoryCustomImpl implements MarketRepositoryCustom {

    private final EntityManager em;

    private final int NUMBER_OF_PAGING = 10;

    @Override
    public List<Market> findMarkets(SortingAndFilteringInfo info, Integer page) {
        // Filtering
        String filteringQuery = "where ";

        // 기간 filtering
        switch (info.getDate()) {
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

        // Sorting
        String sortingQuery = "order by ";

        switch (info.getSort()) {
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
                throw new IllegalArgumentException("Bad Request");
        }

        return em.createQuery("select m from Market m " + filteringQuery + sortingQuery)
                .setParameter("now", LocalDateTime.now())
                .setFirstResult(page * NUMBER_OF_PAGING)
                .setMaxResults(NUMBER_OF_PAGING)
                .getResultList();
    }
}
