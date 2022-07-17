package com.solmi.shorket.market.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "MARKET_TB")
public class Market {
    @Id
    @GeneratedValue
    private Long idx;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String name;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String description;

    private Integer viewCount;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String si;

    @Column(length = 100)
    private String gun;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String gu;

    @Column(length = 1000)
    private String address;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;

    @Temporal(value = TemporalType.TIME)
    @Column(nullable = false)
    private Date startTime;

    @Temporal(value = TemporalType.TIME)
    @Column(nullable = false)
    private Date endTime;

    private Float latitude;

    private Float longitude;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false)
    private Date createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL",
            nullable = false)
    private Date updatedAt;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'Y'")
    @Column(nullable = false)
    private MarketStatusType status;
}