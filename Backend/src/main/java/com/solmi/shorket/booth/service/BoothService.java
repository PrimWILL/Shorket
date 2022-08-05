package com.solmi.shorket.booth.service;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.dto.GetOneBoothDto;
import com.solmi.shorket.booth.repository.BoothRepository;
import com.solmi.shorket.global.exception.GetBoothFailedException;
import com.sun.tools.jconsole.JConsoleContext;
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

    public List<Booth> getAllBy() {
        return boothRepository.findAllBy();
    }


    public GetOneBoothDto getByIdx(int idx) {
        Booth booth = boothRepository.findById(idx).orElseThrow(GetBoothFailedException::new);
        //log.debug(booth.toString());
        return new GetOneBoothDto(booth);
    }

}
