package com.solmi.shorket.market.dto;

import org.springframework.data.domain.Sort;

public enum MarketSortingCriteria {
    VIEW, INTEREST, LATEST, DICT;

    public Sort toSort() {
        Sort sort;
        switch (this) {
            case VIEW:
                sort = Sort.by(Sort.Direction.DESC, "viewCount");
                break;
            case INTEREST:
                sort = Sort.by(Sort.Direction.DESC, "marketInterestCount");
                break;
            case LATEST:
                sort = Sort.by(Sort.Direction.DESC, "createdAt");
                break;
            case DICT:
                sort = Sort.by(Sort.Direction.ASC, "name");
                break;
            default:
                throw new IllegalArgumentException("잘못된 형식의 정렬 기준입니다.");
        }
        return sort;
    }
}
