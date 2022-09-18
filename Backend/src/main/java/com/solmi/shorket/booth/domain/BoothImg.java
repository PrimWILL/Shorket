package com.solmi.shorket.booth.domain;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BOOTH_IMG_TB")
public class BoothImg {
    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "BOOTH_TB_IDX", nullable = false)
    private Booth booth;

    private String url;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BoothImgStatusType status;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false)
    private Date createdAt;

    @Builder
    public BoothImg(Booth booth, String url, BoothImgStatusType status) {
        this.booth = booth;
        this.url = url;
        this.status = status;
    }

}

