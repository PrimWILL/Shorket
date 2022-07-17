package com.solmi.shorket.user.domain;

import com.solmi.shorket.market.domain.Market;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "MARKET_INTEREST_TB")
public class MarketInterest {
    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "Market_TB_IDX", nullable = false)
    private Market market;

    @ManyToOne
    @JoinColumn(name = "USERS_TB_IDX", nullable = false)
    private User user;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false)
    private Date createdAt;
}
