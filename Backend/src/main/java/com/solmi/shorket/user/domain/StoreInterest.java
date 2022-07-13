package com.solmi.shorket.user.domain;

import com.solmi.shorket.store.domain.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "STORE_INTEREST_TB")
public class StoreInterest {
    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "STORE_TB_IDX", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "USERS_TB_IDX", nullable = false)
    private User user;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false)
    private Date createdAt;
}
