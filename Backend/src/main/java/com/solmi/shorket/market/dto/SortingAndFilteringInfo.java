package com.solmi.shorket.market.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SortingAndFilteringInfo {

    private MarketSortingCriteria sort;    // Sorting 기준
    private List<String> locals;   // 지역 Filtering 기준
    private MarketFilteringCriteriaByDate date;    // 기간 Filtering 기준
}