package com.solmi.shorket.booth.domain;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.BoothInterest;
import com.solmi.shorket.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
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
    @JoinColumn(name = "MARKET_TB_IDX", nullable = false)
    private Market market;

    @Column(nullable = false)
    private Integer number;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    @Setter
    private String boothName;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    @Setter
    private String sellerName;

    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    @Setter
    private String item;

    @Column(columnDefinition = "TEXT")
    @Setter
    private String site;

    @Column(length = 1000)
    @Setter
    private String description;

    @Column(columnDefinition = "TEXT")
    @Setter
    private String address;

    @Column(columnDefinition = "VARCHAR(1000) NOT NULL")
    @Setter
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(1000) NOT NULL")
    @Setter
    private String email;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer viewCount;

    @NotNull
    @Setter
    private Date startDate;

    @NotNull
    @Setter
    private Date endDate;

    @NotNull
    @Setter
    private LocalTime startTime;

    @NotNull
    @Setter
    private LocalTime endTime;


    private Float latitude;

    private Float longitude;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'N'")
    @Column(nullable = false)
    private BoothApprovalType approval;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'Waiting'")
    @Column(nullable = false)
    private BoothStatusType status;

    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL)
    private Set<BoothInterest> interests = new HashSet<>();

    public Booth() {

    }

    @Builder
    public Booth(Integer idx, User user, Market market, Integer number, String boothName, String sellerName, String item, String site, String description, String address, String phoneNumber, String email, Integer viewCount, Date startDate, Date endDate,
                 LocalTime startTime, LocalTime endTime, BoothApprovalType approval, BoothStatusType status) {
        this.idx = idx;
        this.user = user;
        this.market = market;
        this.number = number;
        this.boothName = boothName;
        this.sellerName = sellerName;
        this.item = item;
        this.site = site;
        this.description = description;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.viewCount = viewCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approval = approval;
        this.status = status;
    }

    public void addViewCount() {
        this.viewCount++;
    }
}
