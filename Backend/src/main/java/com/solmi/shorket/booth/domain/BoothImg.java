package com.solmi.shorket.booth.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private String Url;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BoothImgStatusType status;

    private Integer ranking;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false)
    private Date createdAt;

}

