package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import com.solmi.shorket.market.domain.Market;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ListMarketResponseDto {

    private Integer marketIdx;
    private String name;
    private Integer interestCount;
    private Integer viewCount;
    private Address address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String thumbnailImage;

    public ListMarketResponseDto(Market market) {
        this.setMarketIdx(market.getIdx());
        this.setName(market.getName());
        this.setInterestCount(market.getMarketInterestCount());
        this.setViewCount(market.getViewCount());
        this.setAddress(market.getAddress());
        this.setOpenTime(market.getOpenTime());
        this.setCloseTime(market.getCloseTime());
        this.setStartDate(market.getStartDate());
        this.setEndDate(market.getEndDate());
        this.setThumbnailImage(market.getImages().get(0).getUrl());
    }
}
