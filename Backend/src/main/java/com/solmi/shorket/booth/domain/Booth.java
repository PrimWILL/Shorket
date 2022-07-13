package com.solmi.shorket.booth.domain;

import com.solmi.shorket.store.domain.Store;
import com.solmi.shorket.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BOOTH_TB")
public class Booth {
    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "USER_TB_IDX", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "STORE_TB_IDX", nullable = false)
    private Store store;

    @Column(nullable = false)
    private Integer number;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String boothName;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String sellerName;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String item;

    @Column(columnDefinition = "TEXT")
    private String site;

    @Column(length = 1000)
    private String description;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "VARCHAR(1000) NOT NULL")
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(1000) NOT NULL")
    private String email;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer viewCount;

    @Temporal(value = TemporalType.DATE)
    private Date startDate;

    @Temporal(value = TemporalType.DATE)
    private Date endDate;

    @Temporal(value = TemporalType.TIME)
    private Date startTime;

    @Temporal(value = TemporalType.TIME)
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
    @ColumnDefault("'N'")
    @Column(nullable = false)
    private BoothApprovalType approval;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'Y'")
    @Column(nullable = false)
    private BoothStatusType status;
}

