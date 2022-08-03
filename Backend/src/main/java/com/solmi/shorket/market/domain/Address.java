package com.solmi.shorket.market.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(length = 100, nullable = false)
    private String si;  // 시

    @Column(length = 100)
    private String gun; // 군

    @Column(length = 100, nullable = false)
    private String gu;  // 구

    @Column(length = 1000)
    private String detail; // 상세 주소
}
