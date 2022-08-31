package com.solmi.shorket.booth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothImg;
import com.solmi.shorket.booth.domain.BoothImgStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@AllArgsConstructor
public class BoothImgDto {

    private Integer idx;
    private Booth booth;
    private String url;
    private Integer ranking;
    private Date createdAt;
    private BoothImgStatusType status;

    public static BoothImgDto boothImgResponse(BoothImg boothImg) {
        return BoothImgDto.builder()
                .url(boothImg.getUrl())
                .status(boothImg.getStatus())
                .ranking(boothImg.getRanking())
                .build();
    }
}
