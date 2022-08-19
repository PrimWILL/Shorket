package com.solmi.shorket.booth.service;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.dto.BoothRequestDto;
import com.solmi.shorket.booth.dto.BoothResponseDto;
import com.solmi.shorket.booth.dto.UpdateBoothDto;
import com.solmi.shorket.booth.repository.BoothRepository;
import com.solmi.shorket.global.exception.BoothNotFoundException;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public BoothResponseDto getByIdx(Integer boothIdx) {
        Booth booth = boothRepository.findById(boothIdx)
                .orElseThrow(BoothNotFoundException::new);
        booth.addViewCount();  // 조회수 증가
        return new BoothResponseDto(booth);
    }

    /**
     * Booth 등록
     */
    @Transactional
    public Booth insertBooth(BoothRequestDto boothRequestDto) {
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
