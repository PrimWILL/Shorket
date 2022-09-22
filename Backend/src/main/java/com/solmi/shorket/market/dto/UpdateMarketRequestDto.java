package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
public class UpdateMarketRequestDto {

    private String name;
    private String description;
    private Address address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> imageUrls;
    private String mapImageUrl;
}
