package com.solmi.shorket.booth.domain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoothImgStatusType {

    THUMBNAIL("thumbnail"), PAMPHLET("pamphlet"), ITEM("item"), ETC("etc");

    private final String key;

}

