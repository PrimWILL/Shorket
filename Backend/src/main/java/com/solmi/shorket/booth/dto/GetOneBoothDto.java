package com.solmi.shorket.booth.dto;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothApprovalType;
import com.solmi.shorket.booth.domain.BoothStatusType;
import lombok.Getter;

import java.util.Date;

@Getter
public class GetOneBoothDto {
    private final Integer idx;
    private final Integer number;
    private final String boothName;
    private final String sellerName;
    private final String item;
    private final String site;
    private final String description;
    private final String address;
    private final String phoneNumber;
    private final String email;
    private final Integer viewCount;
    private final Date startDate;
    private final Date endDate;
    private final Date startTime;
    private final Date endTime;
    private final Float latitude;
    private final Float longitude;
    private final Date createdAt;
    private final Date updatedAt;
    private final BoothApprovalType approval;
    private final BoothStatusType status;

    public GetOneBoothDto(Booth booth) {
        this.idx = booth.getIdx();
        this.boothName = booth.getBoothName();
        this.number = booth.getNumber();
        this.sellerName = booth.getBoothName();
        this.item = booth.getItem();
        this.site = booth.getSite();
        this.description = booth.getDescription();
        this.address = booth.getAddress();
        this.phoneNumber = booth.getPhoneNumber();
        this.email = booth.getEmail();
        this.viewCount = booth.getViewCount();
        this.startDate = booth.getStartDate();
        this.endDate = booth.getEndDate();
        this.startTime = booth.getStartTime();
        this.endTime = booth.getEndTime();
        this.latitude = booth.getLatitude();
        this.longitude = booth.getLongitude();
        this.createdAt = booth.getCreatedAt();
        this.updatedAt = booth.getUpdatedAt();
        this.approval = booth.getApproval();
        this.status = booth.getStatus();
    }
}
