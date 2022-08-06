package com.solmi.shorket.booth.controller;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.dto.GetAllBoothDto;
import com.solmi.shorket.booth.dto.GetOneBoothDto;
import com.solmi.shorket.booth.service.BoothService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Booths")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/booths")
@Slf4j
public class BoothController {

    private final BoothService boothService;

    @ApiOperation(
            value = "부스 목록 조회",
            notes = "전체 부스의 목록을 불러온다."
    )
    @GetMapping("")
    public GetAllBoothResult getAllBooth(){
        List<Booth> findBooths = boothService.getAllBy();
        List<GetAllBoothDto> collect = findBooths.stream().map(GetAllBoothDto::new)
                .collect((Collectors.toList()));
        return new GetAllBoothResult(collect.size(),collect);
    }
    @Data
    @AllArgsConstructor
    static class GetAllBoothResult<T> {
        private int count;
        private T data;
    }

    @ApiOperation(
            value = "부스 정보 조회",
            notes = "boothIdx를 받아서 해당 번호를 가진 부스의 정보를 읽어온다."
    )
    @GetMapping("/{boothIdx}")
    public GetOneBoothDto getBoothInfo(
            @PathVariable Integer boothIdx
    ){
        return boothService.getByIdx(boothIdx);
    }


}