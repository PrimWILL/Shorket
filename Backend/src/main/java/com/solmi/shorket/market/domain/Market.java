package com.solmi.shorket.market.domain;

import com.solmi.shorket.global.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MARKET_TB")
public class Market extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "market_tb_idx")
    private Integer idx;

    @NotNull
    @Column(length = 200)
    private String name;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private Integer viewCount;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    private Float latitude;

    private Float longitude;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'Y'")
    private MarketStatusType status;    // Y: 운영중, C: 운영완료, W: 운영예정

    //== Setter ==//
    private void setName(String name) {
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    private void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    private void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    private void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    private void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}