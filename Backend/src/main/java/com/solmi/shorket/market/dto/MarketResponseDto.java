package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import com.solmi.shorket.market.domain.Market;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MarketResponseDto {

    private Integer marketIdx;
    private String name;
    private String description;
    private Integer viewCount;
    private Address address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer interestCount;

    public MarketResponseDto(Market market) {
        this.setMarketIdx(market.getIdx());
        this.setName(market.getName());
        this.setDescription(market.getDescription());
        this.setViewCount(market.getViewCount());
        this.setAddress(market.getAddress());
        this.setStartDate(market.getStartDate());
        this.setEndDate(market.getEndDate());
        this.setInterestCount(market.getInterests().size());
    }
}
