package com.solmi.shorket.market.controller;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.ListMarketResponseDto;
import com.solmi.shorket.market.dto.MarketReponseDto;
import com.solmi.shorket.market.dto.SortingAndFilteringInfo;
import com.solmi.shorket.market.service.MarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Markets")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/markets")
public class MarketController {

    private final MarketService marketService;

    @ApiOperation(
            value = "Market 목록 조회",
            notes = "기본 정렬 기준: 관심 많은 순]\n" +
                    "\n" +
                    "sort, date는 정수(0, 1, 2, ...) 또는 문자열로 넘겨주시면 됩니다." +
                    "문자열로 작성할 경우, 반드시!! 전부 대문자로 작성해야합니다." +
                    "sort: VIEW(조회순), INTEREST(관심순), LATEST(최신순), DICT(가나다순)" +
                    "locals: 지역 목록이 담긴 배열 (ex. [\"서울\", \"경기도\"]" +
                    "date: UPCOMING(진행 예정), CURRENT(진행 중), COMPLETE(종료)"
    )
    @GetMapping("")
    public List<ListMarketResponseDto> getMarkets(@RequestBody SortingAndFilteringInfo info) {
        List<Market> markets = marketService.findMarkets(info);
        return markets.stream()
                .map(ListMarketResponseDto::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(
            value = "Market 상세 정보 조회",
            notes = "marketIdx에 해당하는 market의 상세 정보 조회"
    )
    @GetMapping("/{marketIdx}")
    public MarketReponseDto getMarket(@PathVariable Integer marketIdx) {
        Market market = marketService.findMarket(marketIdx);
        return new MarketReponseDto(market);
    }
}