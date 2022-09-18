package com.solmi.shorket.booth.service;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothApprovalType;
import com.solmi.shorket.booth.dto.BoothDto;
import com.solmi.shorket.booth.repository.BoothImgRepository;
import com.solmi.shorket.booth.repository.BoothRepository;
import com.solmi.shorket.global.exception.BoothNotFoundException;
import com.solmi.shorket.global.exception.MarketNotFoundException;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.MarketResponseDto;
import com.solmi.shorket.market.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoothService {

    private final BoothRepository boothRepository;
    private final BoothImgRepository boothImgRepository;
    private final MarketRepository marketRepository;

    public Booth findBoothByIdx(Integer boothIdx) {
        Booth booth = boothRepository.findById(boothIdx)
                .orElseThrow(BoothNotFoundException::new);
        return booth;
    }

    /**
     * Booth 목록 조회 By Market
     */
    @Transactional
    public Page<BoothDto> getBoothsByMarket(Pageable pageable, Integer marketIdx) {

        Market market = marketRepository.findById(marketIdx)
                .orElseThrow(MarketNotFoundException::new);

        Page<Booth> booths = boothRepository.findByMarket(pageable, market);

        if (booths.getContent().isEmpty()) {
            throw new BoothNotFoundException();
        }

        return booths.map(BoothDto::boothListResponse);
    }

    /**
     * Booth 전체 목록 조회
     */
    @Transactional
    public Page<BoothDto> getBooths(Pageable pageable) {

        Page<Booth> booths = boothRepository.findAll(pageable);

        return booths.map(BoothDto::boothResponse);
    }

    /**
     * Booth 상세 조회
     */
    public BoothDto getBoothByIdx(Integer boothIdx) {

        BoothDto boothDto = boothRepository.findById(boothIdx)
                .map(booth -> BoothDto.boothResponse(booth, boothImgRepository))
                .orElseThrow(BoothNotFoundException::new);
        // boothDto.addViewCount();  // 조회수 증가
        return boothDto;
    }

    /**
     * Booth 등록
     */
    @Transactional
    public BoothDto createBooth(BoothDto boothDto) {

        Market market = marketRepository.findById(boothDto.getMarket().getMarketIdx())
                .orElseThrow(MarketNotFoundException::new);

        Booth booth = boothRepository.save(boothDto.toEntity(
                market, boothDto.getNumber(), boothDto.getBoothName(),boothDto.getSellerName(),boothDto.getItem(),boothDto.getSite(),boothDto.getDescription(),boothDto.getAddress(),
                boothDto.getPhoneNumber(),boothDto.getEmail(),boothDto.getStartDate(),boothDto.getEndDate(),boothDto.getStartTime(),boothDto.getEndTime()));

        return BoothDto.boothResponse(booth);
    }

    /**
     * Booth 정보 수정
     */
    @Transactional
    public BoothDto updateBooth(Integer boothIdx, BoothDto boothDto) {
        Booth booth = boothRepository.findById(boothIdx)
                .orElseThrow(BoothNotFoundException::new);
        booth = boothRepository.save(boothDto.updateEntity(booth));
        return BoothDto.boothResponse(booth);
    }

    /**
     * Booth 승인
     */
    @Transactional
    public void approveBooth(Integer boothId) {
        Booth booth = boothRepository.findById(boothId)
                .orElseThrow(BoothNotFoundException::new);
        booth.setApproval(BoothApprovalType.Y);
    }

    /**
     * Booth 승인 거절
     */
    @Transactional
    public void notApproveBooth(Integer boothId) {
        Booth booth = boothRepository.findById(boothId)
                .orElseThrow(BoothNotFoundException::new);
        booth.setApproval(BoothApprovalType.N);
    }
}
