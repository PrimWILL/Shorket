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

    // TODO : 역할별 API 접근 권한 관리 필요
    // TODO : 부스 삭제 API -> 관리자가 마음대로 삭제할 수 없게 할 필요가 있음.
    // TODO : 부스 배치도 사진 업로드 API

    private final BoothService boothService;

    @ApiOperation(
            value = "부스 목록 조회",
            notes = "모든 부스 정보를 불러온다."
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
    @PostMapping(value = "")
    public String createBooth(
            @RequestBody @Valid BoothDto boothDto
    ){
        // TODO: Validation 추가 필요
        // validation: 부스 이름 입력 안했을 때
        if(boothDto.getBoothName().isEmpty()){
            throw new BoothNotFoundException();
        }

        try {
            // TODO: 이미지도 등록할 수 있어야 한다.
            Integer boothIdx = boothService.insertBooth(boothDto);

            return boothIdx + " 부스 등록이 완료되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(
            value = "부스 정보 수정",
            notes = "마켓 관리자는 부스 정보 수정이 가능하다."
    )
    @PutMapping("/{boothIdx}")
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

    @ApiOperation(
            value = "부스 신청 승인",
            notes = "마켓 관리자는 부스 신청을 승인할 수 있다."
    )
    @PatchMapping("/{boothIdx}/approve")
    public String approveBooth(
            @PathVariable Integer boothIdx
    ){
        boothService.approveBooth(boothIdx);
        return "부스 승인이 완료되었습니다.";
    }

    @ApiOperation(
            value = "부스 신청 거절",
            notes = "마켓 관리자는 부스 신청을 거절할 수 있다."
    )
    @PatchMapping("/{boothIdx}/notApprove")
    public String notApproveBooth(
            @PathVariable Integer boothIdx
    ){
        boothService.notApproveBooth(boothIdx);
        return "부스 승인 거절이 완료되었습니다.";
    }

}