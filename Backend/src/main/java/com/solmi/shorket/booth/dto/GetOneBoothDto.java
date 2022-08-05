package com.solmi.shorket.booth.dto;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothApprovalType;
import com.solmi.shorket.booth.domain.BoothStatusType;
import com.solmi.shorket.store.domain.Store;
import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.User;

import java.util.Date;

public class GetOneBoothDto {
    private Integer idx;
    private Integer number;
    private String boothName;
    private String sellerName;
    private String item;
    private String site;
    private String description;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer viewCount;
    private Date startDate;
    private Date endDate;
    private Date startTime;
    private Date endTime;
    private Float latitude;
    private Float longitude;
    private Date createdAt;
    private Date updatedAt;
    private BoothApprovalType approval;
    private BoothStatusType status;
    private int count;
    // private User user;
    // private Store store;

    public GetOneBoothDto(Booth booth) {
        this.idx = booth.getIdx();
        this.boothName = booth.getBoothName();
        this.number = booth.getNumber();
        this.boothName = booth.getBoothName();
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
