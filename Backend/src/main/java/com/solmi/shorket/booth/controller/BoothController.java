package com.solmi.shorket.booth.controller;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.dto.*;
import com.solmi.shorket.booth.service.BoothImgService;
import com.solmi.shorket.booth.service.BoothInterestService;
import com.solmi.shorket.booth.service.BoothService;
import com.solmi.shorket.global.exception.BoothImgNotFoundException;
import com.solmi.shorket.global.exception.BoothNotFoundException;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.service.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Api(tags = "Booths")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/booths")
@Slf4j
public class BoothController {

    // TODO : 역할별 API 접근 권한 관리 필요
    // TODO : 부스 삭제 API -> 관리자가 마음대로 삭제할 수 없게 할 필요가 있음.
    // TODO : 부스 배치도 사진 업로드 API
    // TODO : 부스 number 수정 API. 드래그 앤 드롭 형식. 부스 번호를 배정한다.
    // TODO : return type 마켓과 통일 (void?)

    private final BoothService boothService;
    private final BoothInterestService boothInterestService;
    private final BoothImgService boothImgService;
    private final SecurityService securityService;

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
        return boothService.getBoothByIdx(boothIdx);
    }

    @ApiOperation(
            value = "부스 등록",
            notes = "판매자가 마켓 관리자에게 부스(셀러) 신청을 한다.\n"
    )
    @PostMapping(value = "")
    public BoothDto createBooth(
            @RequestBody @Valid BoothDto boothDto
    ){
        // TODO: Validation 추가 필요
        // validation: 부스 이름 입력 안했을 때
        if(boothDto.getBoothName().isEmpty()){
            throw new BoothNotFoundException();
        }

        try {
            return boothService.createBooth(boothDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(
            value = "부스 이미지 등록",
            notes = "부스 이미지를 등록한다.\n"
    )
    @PostMapping(value = "/{boothIdx}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createBoothImg(
            @PathVariable Integer boothIdx,
            @RequestPart(value = "image") MultipartFile image,
            @RequestPart(value = "dto") @Parameter(schema =@Schema(type = "string", format = "binary")) BoothImgDto boothImgDto
    ) {
        // validation: 파일이 비어 있을 경우
        if (image.isEmpty()) {
            throw new BoothImgNotFoundException();
        }

        try {
            String imageUrl = boothImgService.addBoothImg(boothIdx, image, boothImgDto);

            return imageUrl;
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
    public BoothDto updateBoothInfo(
            @PathVariable Integer boothIdx,
            @RequestBody BoothDto boothDto
    ){
        try {
            return boothService.updateBooth(boothIdx, boothDto);
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

    @ApiOperation(
            value = "관심 부스 추가",
            notes = "`X-AUTH-TOKEN`에 해당하는 user가 `boothIdx`에 해당하는 booth을 관심 booth로 추가한다."
    )
    @GetMapping("/{boothIdx}/like")
    public void addMarketInterest(@RequestHeader("X-AUTH-TOKEN") String token, @PathVariable Integer boothIdx) {
        User user = securityService.findUserByAccessToken(token);
        Booth booth = boothService.findBoothByIdx(boothIdx);

        boothInterestService.addInterest(user, booth);
    }

    @ApiOperation(
            value = "관심 부스 취소",
            notes = "`X-AUTH-TOKEN`에 해당하는 user의 관심 booth 목록에서 `boothIdx`에 해당하는 booth을 삭제한다."
    )
    @DeleteMapping("/{boothIdx}/like")
    public void cancelMarketInterest(@RequestHeader("X-AUTH-TOKEN") String token, @PathVariable Integer boothIdx) {
        User user = securityService.findUserByAccessToken(token);
        Booth booth = boothService.findBoothByIdx(boothIdx);

        boothInterestService.cancelInterest(user, booth);
    }

}