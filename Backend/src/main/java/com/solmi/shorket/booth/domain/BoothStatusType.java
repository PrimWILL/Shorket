package com.solmi.shorket.booth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoothStatusType {
    // OPEN: 운영중, CLOSE: 운영완료, WAIT: 운영예정

    OPEN("open"), CLOSE("close"), WAIT("wait");

    private final String key;

}
