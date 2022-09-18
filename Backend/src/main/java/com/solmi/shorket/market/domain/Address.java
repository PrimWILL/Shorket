package com.solmi.shorket.market.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {

    @Column(length = 100, nullable = false)
    private String sido;  // 특별시, 광역시, 도

    @Column(length = 100, nullable = false)
    private String sigungu; // 시, 군, 구

    @Column(length = 1000)
    private String detailAddress; // 상세 주소
}
