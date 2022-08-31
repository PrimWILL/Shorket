package com.solmi.shorket.booth.controller;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.dto.*;
import com.solmi.shorket.booth.service.BoothService;
import com.solmi.shorket.global.exception.BoothNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Booths")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/booths")
@Slf4j
public class BoothController {

    // TODO: 역할별 API 접근 권한 관리 필요

    private final BoothService boothService;

    @ApiOperation(
            value = "부스 목록 조회",
            notes = "모든 부스를 불러온다."
    )
    @GetMapping("")
    public Page<BoothDto> getBoothsByMarket(
            @PageableDefault(size = 10) Pageable pageable
    ){
        return boothService.getBooths(pageable);
    }

    @ApiOperation(
            value = "부스 정보 조회",
            notes = "boothIdx를 받아서 해당 번호를 가진 부스의 정보를 읽어온다."
    )
    @GetMapping("/{boothIdx}")
    public BoothDto getBoothInfo(
            @PathVariable Integer boothIdx
    ){
        return boothService.getByIdx(boothIdx);
    }

    @ApiOperation(
            value = "부스 등록",
            notes = "판매자가 마켓 관리자에게 부스(셀러) 신청을 한다.\n"
    )
    @PostMapping(value = "/create")
    public String createBooth(
            @RequestBody @Valid BoothDto boothDto
    ){
        // TODO: 이미지도 등록할 수 있어야 한다.
        // TODO: Validation 추가 필요
        // validation: 부스 이름 입력 안했을 때
        if(boothDto.getBoothName().isEmpty()){
            throw new BoothNotFoundException();
        }

        try {
            Integer boothIdx = boothService.insertBooth(boothDto);

            return boothIdx + " 부스 등록이 완료되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(
            value = "부스 수정",
            notes = "부스 정보 수정이 가능하다"
    )
    @PutMapping("/{boothId}")
    public String updateBoothInfo(
            @PathVariable Integer boothIdx,
            @RequestBody BoothDto boothDto
    ){
        try {
            // TODO: 이미지도 등록할 수 있어야 한다.
            boothService.updateBooth(boothIdx, boothDto);
            return "부스 수정이 완료되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // TODO : 부스 삭제 API(부스 종료 API), 부스 배치도 사진 업로드 API, 부스 승인 API, 부스 승인 거절 API

}