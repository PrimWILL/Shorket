package com.solmi.shorket.market.domain;

import com.solmi.shorket.global.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "MARKET_TB")
public class Market extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long idx;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    private String name;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private Integer viewCount;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String si;  // 시

    @Column(length = 100)
    private String gun; // 군

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String gu;  // 구

    @Column(length = 1000)
    private String address; // 상세 주소

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

    private void setSi(String si) {
        this.si = si;
    }

    private void setGun(String gun) {
        this.gun = gun;
    }

    private void setGu(String gu) {
        this.gu = gu;
    }

    private void setAddress(String address) {
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

    private void setStatus(MarketStatusType status) {
        this.status = status;
    }
}