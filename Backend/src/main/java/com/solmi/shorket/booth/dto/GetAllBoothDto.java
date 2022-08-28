package com.solmi.shorket.booth.dto;

import com.solmi.shorket.booth.domain.Booth;
import lombok.Getter;

@Getter
public class GetAllBoothDto {
    private final String boothName; // 부스 이름
    private final String item; // 부스 종류 (판매품목)

    public GetAllBoothDto(Booth booth) {
        this.boothName = booth.getBoothName();
        this.item = booth.getItem();
    }
}
