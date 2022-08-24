package com.solmi.shorket.booth.dto;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothStatusType;
import lombok.Getter;

@Getter
public class ListBoothResponseDto {
    private final Integer number;
    private final String boothName; // 부스 이름
    private final String item; // 부스 종류 (판매품목)
    private final BoothStatusType status; //

    public ListBoothResponseDto(Booth booth) {
        this.number = booth.getNumber();
        this.boothName = booth.getBoothName();
        this.item = booth.getItem();
        this.status = booth.getStatus();
    }
}
