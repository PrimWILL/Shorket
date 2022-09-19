package com.solmi.shorket.market.domain;

import com.solmi.shorket.global.BaseTimeEntity;
import com.solmi.shorket.user.domain.MarketInterest;
import com.solmi.shorket.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MARKET_TB")
public class Market extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Integer idx;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_tb_idx")
    private User manager;

    @NotNull
    @Column(length = 200)
    private String name;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private Integer viewCount;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    private LocalTime openTime;

    @NotNull
    private LocalTime closeTime;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

//    private Float latitude;
//
//    private Float longitude;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<MarketImage> images = new ArrayList<>();

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "map_image_tb_idx")
    private MarketImage mapImage;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private Set<MarketInterest> interests = new HashSet<>();

    @Formula("(select count(1) " +
            "from market_interest_tb mi " +
            "where mi.idx=idx)")
    private int marketInterestCount;

    //== Constructor ==//
    private Market(User manager, String name, String description, Integer viewCount, Address address,
                   LocalTime openTime, LocalTime closeTime, LocalDateTime startDate, LocalDateTime endDate) {
        this.manager = manager;
        this.name = name;
        this.description = description;
        this.viewCount = viewCount;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //== Create Method ==//
    public static Market createMarket(User manager, String name, String description, Address address,
                                      LocalTime openTime, LocalTime closeTime,
                                      LocalDateTime startDate, LocalDateTime endDate,
                                      List<String> imageUrls, String mapImageUrl) {
        Market market = new Market(manager, name, description, 0, address, openTime, closeTime, startDate, endDate);
        imageUrls.forEach(imageUrl -> market.getImages().add(new MarketImage(market, Market.parseImageUrl(imageUrl))));
        market.setMapImage(new MarketImage(Market.parseImageUrl(mapImageUrl)));

        return market;
    }

    //== Util Method ==//
    private static String parseImageUrl(String url) {
        int pos = url.lastIndexOf("id=");
        return "https://drive.google.com/uc?export=view&id=" + url.substring(pos + 3);
    }

    //== Business Logic ==//
    public void addViewCount() {
        this.viewCount++;
    }

    public void update(String name, String description, Address address,
                       LocalDateTime startDate, LocalDateTime endDate,
                       List<String> imageUrls, String mapImageUrl) {
        this.setName(name);
        this.setDescription(description);
        this.setAddress(address);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        imageUrls.forEach(imageUrl -> this.getImages().add(new MarketImage(this, Market.parseImageUrl(imageUrl))));
        this.setMapImage(new MarketImage(mapImageUrl));
    }

    //== Setter ==//
    private void setName(String name) {
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    private void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    private void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    private void setMapImage(MarketImage mapImage) {
        this.mapImage = mapImage;
    }
}