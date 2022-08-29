package com.solmi.shorket.booth.service;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.dto.BoothDto;
import com.solmi.shorket.booth.repository.BoothImgRepository;
import com.solmi.shorket.booth.repository.BoothRepository;
import com.solmi.shorket.global.exception.BoothNotFoundException;
import com.solmi.shorket.global.exception.MarketNotFoundException;
import com.solmi.shorket.market.domain.Market;
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

    /**
     * Booth 목록 조회
     */
    @Transactional
    public Page<BoothDto> getBoothsByMarket(Pageable pageable, Integer marketIdx) {

        Market market = marketRepository.findById(marketIdx)
                .orElseThrow(MarketNotFoundException::new);

        Page<Booth> booths = boothRepository.getByMarket(pageable, market);

        if (booths.getContent().isEmpty()) {
            throw new BoothNotFoundException();
        }

        return booths.map(BoothDto::boothListResponse);
    }

    /**
     * Booth 상세 조회
     */
    public BoothDto getByIdx(Integer boothIdx) {

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
    public Integer insertBooth(BoothDto boothDto) {
        // TODO : validation
        Booth booth = boothRepository.save(boothDto.toEntity());
        return booth.getIdx();
    }

    /**
     * Booth 정보 수정
     */
    @Transactional
    public BoothDto updateBooth(Integer boothId, BoothDto boothDto) {
        Booth booth = boothRepository.findById(boothId)
                .orElseThrow(BoothNotFoundException::new);
        booth = boothRepository.save(boothDto.updateEntity(booth));
        return BoothDto.boothResponse(booth);
    }
}
