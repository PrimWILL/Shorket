package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import com.solmi.shorket.market.domain.Market;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListMarketResponseDto {

    private Integer marketIdx;
    private String name;
    private Integer interestCount;
    private Integer viewCount;
    private Address address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ListMarketResponseDto(Market market) {
        this.setMarketIdx(market.getIdx());
        this.setName(market.getName());
        this.setInterestCount(market.getInterests().size());
        this.setViewCount(market.getViewCount());
        this.setAddress(market.getAddress());
        this.setStartDate(market.getStartDate());
        this.setEndDate(market.getEndDate());
    }
}
