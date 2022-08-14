package com.solmi.shorket.user.domain;

import com.solmi.shorket.market.domain.Market;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

/**
 * How to create MarketInterest Object?
 * - MarketInterest.createMarketInterest(User user, Market market)
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MARKET_INTEREST_TB")
public class MarketInterest {

    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "market_tb_idx", nullable = false)
    private Market market;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_tb_idx", nullable = false)
    private User user;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    //== 생성 메서드 ==//
    public static MarketInterest createMarketInterest(User user, Market market) {
        MarketInterest marketInterest = new MarketInterest();
        marketInterest.setUser(user);
        marketInterest.setMarket(market);
        return marketInterest;
    }

    //== 연관관계 메서드 ==//
    private void setMarket(Market market) {
        this.market = market;
        market.getInterests().add(this);
    }

    private void setUser(User user) {
        this.user = user;
//        user.getMarketInterests().add(this);
    }
}
