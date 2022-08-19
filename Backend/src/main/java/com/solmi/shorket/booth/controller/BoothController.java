package com.solmi.shorket.booth.controller;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.dto.BoothRequestDto;
import com.solmi.shorket.booth.dto.ListBoothResponseDto;
import com.solmi.shorket.booth.dto.BoothResponseDto;
import com.solmi.shorket.booth.dto.UpdateBoothDto;
import com.solmi.shorket.booth.service.BoothService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.*;

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
    public ListBoothReponseResult getAllBooth(){
        // TODO : BoothApprovalType, Booth 좋아요 여부 추가
        List<Booth> findBooths = boothService.getAllBy();
        List<ListBoothResponseDto> collect = findBooths.stream().map(ListBoothResponseDto::new)
                .collect((Collectors.toList()));
        return new ListBoothReponseResult(collect.size(),collect);
    }
    @Data
    @AllArgsConstructor
    static class ListBoothReponseResult<T> {
        private int count;
        private T data;
    }

    @ApiOperation(
            value = "부스 정보 조회",
            notes = "boothIdx를 받아서 해당 번호를 가진 부스의 정보를 읽어온다."
    )
    @GetMapping("/{boothIdx}")
    public BoothResponseDto getBoothInfo(
            @PathVariable Integer boothIdx
    ){
        // TODO: 이미지도 불러와야 한다.
        return boothService.getByIdx(boothIdx);
    }

    @ApiOperation(
            value = "부스 등록",
            notes = "판매자가 마켓 관리자에게 부스(셀러) 신청을 한다.\n"
    )
    @PostMapping("/")
    public String createBooth(
            @RequestPart(value = "dto") @Parameter(schema =@Schema(type = "string", format = "binary")) BoothRequestDto boothRequestDto
    ){
        // TODO: Validation 추가
        // TODO: 이미지도 등록할 수 있어야 한다.
        boothService.insertBooth(boothRequestDto);
        return "부스 등록이 완료되었습니다.";
    }

    @ApiOperation(
            value = "부스 수정",
            notes = "부스 정보 수정이 가능하다"
    )
    @PatchMapping("/{boothId}")
    public String updateBoothInfo(
            @PathVariable Integer boothIdx,
            @RequestPart(value = "dto") @Parameter(schema =@Schema(type = "string", format = "binary")) UpdateBoothDto updateBoothDto
    ){
        boothService.updateBooth(boothIdx, updateBoothDto);
        return "부스 수정이 완료되었습니다.";
    }

    // TODO : 부스 삭제 API(부스 종료 API), 부스 배치도 사진 업로드 API, 부스 승인 API, 부스 승인 거절 API

}