package com.solmi.shorket.store.domain;

import com.solmi.shorket.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "STORE_IMG_TB")
public class StoreImg {
    @Id
    @GeneratedValue
    private Long storeImgIdx;

    @ManyToOne
    @JoinColumn(name = "STORE_TB_IDX", nullable = false)
    private Store store;

    private Integer ranking;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false)
    private Date createdAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private StoreImgStatusType status;
}

