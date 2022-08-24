package com.solmi.shorket.booth.domain;

import com.solmi.shorket.booth.dto.UpdateBoothDto;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.BoothInterest;
import com.solmi.shorket.user.domain.User;
import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

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

    public Booth() {

    }

    //== update ==//

    public void addViewCount() {
        this.viewCount++;
    }

    public void update(UpdateBoothDto boothDto) {
        this.setBoothName(boothDto.getBoothName());
        this.setSellerName(boothDto.getSellerName());
        this.setItem(boothDto.getItem());
        this.setSite(boothDto.getSite());
        this.setDescription(boothDto.getDescription());
        this.setAddress(boothDto.getAddress());
        this.setPhoneNumber(boothDto.getPhoneNumber());
        this.setEmail(boothDto.getEmail());
        this.setStartDate(boothDto.getStartDate());
        this.setEndDate(boothDto.getEndDate());
    }

    //== Setter ==//

    private void setUser(User user) {this.user = user;}

    private void setMarket(Market market) {this.market = market;}

    private void setNumber(Integer number) {this.number = number;}

    private void setBoothName(String boothName) {this.boothName = boothName;}

    private void setSellerName(String sellerName) {this.sellerName = sellerName;}

    private void setItem(String item) {this.item = item;}

    private void setSite(String site) {this.site = site;}

    private void setDescription(String description) {
        this.description = description;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    private void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    //== Builder ==//

    @Builder
    public Booth(Integer idx, User user, Market market, Integer number, String boothName, String sellerName, String item, String site, String description, String address, String phoneNumber, String email, Integer viewCount, LocalDateTime startDate, LocalDateTime endDate, BoothApprovalType approval, BoothStatusType status) {
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
        this.approval = approval;
        this.status = status;
    }
}

    @OneToMany(mappedBy = "booth", cascade = CascadeType.ALL)
    private Set<BoothInterest> interests = new HashSet<>();
}
