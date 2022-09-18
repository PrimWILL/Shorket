package com.solmi.shorket.market.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MARKET_IMAGE_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MarketImage {

    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_tb_idx")
    private Market market;

    @NotNull
    private String url;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    //== Constructor ==//
    public MarketImage(String url) {
        this(null, url);
    }

    public MarketImage(Market market, String url) {
        this.market = market;
        this.url = url;
    }
}

