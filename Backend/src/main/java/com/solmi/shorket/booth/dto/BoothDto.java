package com.solmi.shorket.booth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothApprovalType;
import com.solmi.shorket.booth.domain.BoothStatusType;
import com.solmi.shorket.market.domain.Market;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@AllArgsConstructor
public class BoothDto {
    private final Integer idx;
    private final Integer number;
    private final String boothName;
    private final String sellerName;
    private final String item;
    private final String site;
    private final String description;
    private final String address;
    private final String phoneNumber;
    private final String email;
    private final Integer viewCount;
    private final Date startDate;
    private final Date endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final BoothApprovalType approval;
    private final BoothStatusType status;

    public static BoothDto boothListResponse(Booth booth) {
        return BoothDto.builder()
            .number(booth.getNumber())
            .boothName(booth.getBoothName()) // 부스 이름
            .item(booth.getItem()) // 부스 종류 (판매품목)
            .status(booth.getStatus())
            .build();
    }

    public static BoothDto boothResponse(Booth booth) {
        return BoothDto.builder()
            .idx(booth.getIdx())
            .number(booth.getNumber())
            .boothName(booth.getBoothName())
            .sellerName(booth.getSellerName())
            .item(booth.getItem())
            .site(booth.getSite())
            .description(booth.getDescription())
            .address(booth.getAddress())
            .phoneNumber(booth.getPhoneNumber())
            .email(booth.getEmail())
            .viewCount(booth.getViewCount())
            .startDate(booth.getStartDate())
            .endDate(booth.getEndDate())
            .startTime(booth.getStartTime())
            .endTime(booth.getEndTime())
            .approval(booth.getApproval())
            .status(booth.getStatus())
            .build();
    }

    // BoothRequestDto -> Booth
    // 부스 생성
    public Booth toEntity() {
        return Booth.builder()
                //.market(market)
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

    public Booth updateEntity(Booth booth) {
        Optional.ofNullable(boothName).ifPresent(booth::setBoothName);
        Optional.ofNullable(sellerName).ifPresent(booth::setSellerName);
        Optional.ofNullable(item).ifPresent(booth::setItem);
        Optional.ofNullable(site).ifPresent(booth::setSite);
        Optional.ofNullable(description).ifPresent(booth::setDescription);
        Optional.ofNullable(address).ifPresent(booth::setAddress);
        Optional.ofNullable(phoneNumber).ifPresent(booth::setPhoneNumber);
        Optional.ofNullable(email).ifPresent(booth::setEmail);
        Optional.ofNullable(startDate).ifPresent(booth::setStartDate);
        Optional.ofNullable(endDate).ifPresent(booth::setEndDate);
        Optional.ofNullable(startTime).ifPresent(booth::setStartTime);
        Optional.ofNullable(endTime).ifPresent(booth::setEndTime);

        return booth;
    }

}
