package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Url Format이 정확한지 검증 필요
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMarketRequestDto {
    private String userEmail;
    private String name;
    private String description;
    private Address address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> imageUrls;
    private String mapImageUrl;

    public Market toEntity(User user) {
        return Market.createMarket(user, name, description, address, openTime, closeTime, startDate, endDate, imageUrls, mapImageUrl);
    }
}
