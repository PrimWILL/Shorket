package com.solmi.shorket.market.dto;

import com.solmi.shorket.market.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NotNull
@AllArgsConstructor
public class UpdateMarketDto {

    private String name;
    private String description;
    private Address address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
