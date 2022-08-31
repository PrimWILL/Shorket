package com.solmi.shorket.booth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothApprovalType;
import com.solmi.shorket.booth.domain.BoothImg;
import com.solmi.shorket.booth.domain.BoothStatusType;
import com.solmi.shorket.booth.repository.BoothImgRepository;
import com.solmi.shorket.market.domain.Market;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@AllArgsConstructor
public class BoothDto {
    private Integer idx;
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
    private Date startDate;
    private Date endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BoothApprovalType approval;
    private BoothStatusType status;
    private List<BoothImgDto> boothImgs;
    private Market market;

    public static BoothDto boothListResponse(Booth booth) {
        return BoothDto.builder()
            .number(booth.getNumber())
            .boothName(booth.getBoothName()) // 부스 이름
            .item(booth.getItem()) // 부스 종류 (판매품목)
            .status(booth.getStatus())
            .build();
    }

    public static BoothDto boothResponse(Booth booth) {
        return boothResponse(booth, null);
    }

    public static BoothDto boothResponse(Booth booth, BoothImgRepository boothImgRepository) {
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
            .boothImgs(Optional.ofNullable(boothImgRepository).map(
                    e -> e.findByBoothIdx(booth.getIdx()).stream().map(BoothImgDto::boothImgResponse).collect(Collectors.toList())).orElse(null))
            .market(booth.getMarket())
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
