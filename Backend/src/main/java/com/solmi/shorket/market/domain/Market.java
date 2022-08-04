package com.solmi.shorket.market.domain;

import com.solmi.shorket.global.BaseTimeEntity;
import com.solmi.shorket.user.domain.MarketInterest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "market")
    private Set<MarketInterest> interests = new HashSet<>();

//    @NotNull
//    @Enumerated(value = EnumType.STRING)
//    @ColumnDefault("'W'")
//    private MarketStatusType status;    // Y: 운영중, C: 운영완료, W: 운영예정

    //== 생성 메서드 ==//
    public static Market createMarket(String name, String description, Address address, LocalDateTime startDate, LocalDateTime endDate) {
        return new Market(name, description, 0, address, startDate, endDate);
    }

    //== 수정 메서드==//
    public void addViewCount() {
        this.viewCount++;
    }

    //== Constructor ==//
    private Market(String name, String description, Integer viewCount, Address address, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.description = description;
        this.viewCount = viewCount;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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