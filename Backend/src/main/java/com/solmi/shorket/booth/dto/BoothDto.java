package com.solmi.shorket.booth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothApprovalType;
import com.solmi.shorket.booth.domain.BoothStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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

    public static BoothDto BoothListResponse(Booth booth) {
        return BoothDto.builder()
            .number(booth.getNumber())
            .boothName(booth.getBoothName()) // 부스 이름
            .item(booth.getItem()) // 부스 종류 (판매품목)
            .status(booth.getStatus())
            .build();
    }

    public static BoothDto BoothResponse(Booth booth) {
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
}
