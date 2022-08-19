package com.solmi.shorket.booth.dto;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothApprovalType;
import com.solmi.shorket.booth.domain.BoothStatusType;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.User;

import java.time.LocalDateTime;

public class BoothRequestDto {

    private Integer idx;
    private User user;
    private Market market;
    private Integer number;
    private String boothName;
    private String sellerName;
    private String item;
    private String site;
    private String description;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer viewCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BoothApprovalType approval;
    private BoothStatusType status;

    // BoothRequestDto -> Booth
    public Booth toEntity() {
        return Booth.builder()
                .user(user)
                .market(market)
                .number(number)
                .boothName(boothName)
                .sellerName(sellerName)
                .item(item)
                .site(site)
                .description(description)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .startDate(startDate)
                .endDate(endDate)
                .viewCount(0)
                .build();
    }
}
