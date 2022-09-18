package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import com.solmi.shorket.market.domain.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Url Format이 정확한지 검증 필요
 */

@Getter
@Setter
@AllArgsConstructor
public class CreateMarketRequestDto {
    private String name;
    private String description;
    private Address address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> imageUrls;
    private String mapImageUrl;

    public Market toEntity() {
        return Market.createMarket(name, description, address, startDate, endDate, imageUrls, mapImageUrl);
    }
}
