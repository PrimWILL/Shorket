package com.solmi.shorket.booth.service;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.dto.BoothDto;
import com.solmi.shorket.booth.dto.CreateBoothRequestDto;
import com.solmi.shorket.booth.dto.BoothResponseDto;
import com.solmi.shorket.booth.dto.UpdateBoothDto;
import com.solmi.shorket.booth.repository.BoothRepository;
import com.solmi.shorket.global.exception.BoothNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoothService {

    private final BoothRepository boothRepository;

    /**
     * Booth 목록 조회
     */
    public List<Booth> getAllBy() {
        return boothRepository.findAllBy();
    }

    /**
     * Booth 상세 조회
     */
    public BoothDto getByIdx(Integer boothIdx) {

        BoothDto boothDto = boothRepository.findById(boothIdx)
                .map(booth -> BoothDto.BoothResponse(booth))
                .orElseThrow(BoothNotFoundException::new);

        // boothDto.addViewCount();  // 조회수 증가
        return boothDto;
    }

    /**
     * Booth 등록
     */
    @Transactional
    public Booth insertBooth(CreateBoothRequestDto boothRequestDto) {
        // TODO : validation
        Booth booth = boothRepository.save(boothRequestDto.toEntity());
        return boothRepository.save(booth);
    }

    /**
     * Booth 정보 수정
     */
    @Transactional
    public void updateBooth(Integer boothId, UpdateBoothDto updateBoothDto) {
        Booth booth = boothRepository.findById(boothId)
                .orElseThrow(BoothNotFoundException::new);
        booth.update(updateBoothDto);
    }
}
