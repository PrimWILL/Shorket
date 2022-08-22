package com.solmi.shorket.market.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateMarketRequestDto {
    private Long marketId;
    private String name;
    private String description;
    private String sido;
    private String sigungu;
    private String detailAddress;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
