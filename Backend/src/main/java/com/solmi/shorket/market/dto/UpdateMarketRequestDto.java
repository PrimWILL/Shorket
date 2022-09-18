package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateMarketRequestDto {

    private String name;
    private String description;
    private Address address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> imageUrls;
    private String mapImageUrl;
}
