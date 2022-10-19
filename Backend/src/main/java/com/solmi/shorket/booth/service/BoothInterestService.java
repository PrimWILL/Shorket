package com.solmi.shorket.booth.service;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothInterest;
import com.solmi.shorket.booth.repository.BoothInterestRepository;
import com.solmi.shorket.global.exception.BoothInterestNotFoundException;
import com.solmi.shorket.global.exception.DuplicateBoothInterestException;
import com.solmi.shorket.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoothInterestService {
    private final BoothInterestRepository boothInterestRepository;

    /**
     * 관심 마켓 추가
     */
    @Transactional
    public String addInterest(User user, Booth booth) {
        if (boothInterestRepository.existsByUserAndBooth(user, booth)) {
            throw new DuplicateBoothInterestException();
        }
        BoothInterest boothInterest = boothInterestRepository.save(BoothInterest.of(user, booth));
        return "좋아요 등록이 완료되었습니다.";
    }

    /**
     * 관심 마켓 취소
     */
    @Transactional
    public void cancelInterest(User user, Booth booth) {
        boothInterestRepository.findByUserAndBooth(user, booth)
                .orElseThrow(BoothInterestNotFoundException::new);
        boothInterestRepository.deleteByUserAndBooth(user,booth);
    }

}
