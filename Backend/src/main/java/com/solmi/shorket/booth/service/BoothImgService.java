package com.solmi.shorket.booth.service;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothImg;
import com.solmi.shorket.booth.domain.BoothImgStatusType;
import com.solmi.shorket.booth.dto.BoothImgDto;
import com.solmi.shorket.booth.repository.BoothImgRepository;
import com.solmi.shorket.booth.repository.BoothRepository;
import com.solmi.shorket.global.exception.BoothNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoothImgService {

    private final BoothRepository boothRepository;
    private final BoothImgRepository boothImgRepository;
    private final S3UploaderService s3UploaderService;

    /**
     * 이미지 등록
     */
    @Transactional
    public String addBoothImg(Integer boothIdx, MultipartFile image, BoothImgDto boothImgDto) {

        Booth booth = boothRepository.findById(boothIdx)
                .orElseThrow(BoothNotFoundException::new);

        String imageUrl = null;
        try {
            imageUrl = s3UploaderService.upload(image);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        BoothImgStatusType status = boothImgDto.getStatus();

        BoothImg boothImg = boothImgRepository.save(boothImgDto.toEntity(booth, imageUrl, status));

        return imageUrl;
    }


}
