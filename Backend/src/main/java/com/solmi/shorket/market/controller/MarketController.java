package com.solmi.shorket.market.controller;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.ListMarketResponseDto;
import com.solmi.shorket.market.dto.MarketReponseDto;
import com.solmi.shorket.market.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.*;

@RestController
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    /**
     * Market 목록 조회
     */
    @GetMapping("/markets")
    public Page<ListMarketResponseDto> getMarkets(@PageableDefault(sort = "viewCount", direction = DESC) Pageable pageable) {
        return marketService.findMarkets(pageable)
                .map(ListMarketResponseDto::new);
    }

    /**
     * Market 단건 조회
     */
    @GetMapping("/market/{marketIdx}")
    public MarketReponseDto getMarket(@PathVariable Integer marketIdx) {
        Market market = marketService.findMarket(marketIdx);
        return new MarketReponseDto(market);
    }
}