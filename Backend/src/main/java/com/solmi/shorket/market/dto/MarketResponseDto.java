package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import com.solmi.shorket.market.domain.Market;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MarketResponseDto {

    private Integer marketIdx;
    private String name;
    private String description;
    private Integer viewCount;
    private Address address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer interestCount;
    private List<String> images = new ArrayList<>();
    private String mapImage;

    public MarketResponseDto(Market market) {
        this.setMarketIdx(market.getIdx());
        this.setName(market.getName());
        this.setDescription(market.getDescription());
        this.setViewCount(market.getViewCount());
        this.setAddress(market.getAddress());
        this.setStartDate(market.getStartDate());
        this.setEndDate(market.getEndDate());
        this.setInterestCount(market.getMarketInterestCount());
        market.getImages().forEach(img -> this.getImages().add(img.getUrl()));
        this.setMapImage(market.getMapImage().getUrl());
    }
}
