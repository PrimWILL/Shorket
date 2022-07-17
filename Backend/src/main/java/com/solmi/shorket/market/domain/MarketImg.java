package com.solmi.shorket.market.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "MARKET_IMG_TB")
public class MarketImg {
    @Id
    @GeneratedValue
    private Long marketImgIdx;

    @ManyToOne
    @JoinColumn(name = "MARKET_TB_IDX", nullable = false)
    private Market market;

    private Integer ranking;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false)
    private Date createdAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MarketImgStatusType status;
}

