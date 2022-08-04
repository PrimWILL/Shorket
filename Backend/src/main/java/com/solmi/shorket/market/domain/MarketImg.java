package com.solmi.shorket.market.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MARKET_IMG_TB")
public class MarketImg {

    @Id
    @GeneratedValue
    @Column(name = "store_img_idx")
    private Integer idx;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "MARKET_TB_IDX")
    private Market market;

    private Integer priority;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private MarketImgStatusType status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    //== Setter ==//
    private void setMarket(Market market) {
        this.market = market;
    }

    private void setPriority(Integer priority) {
        this.priority = priority;
    }

    private void setStatus(MarketImgStatusType status) {
        this.status = status;
    }
}

