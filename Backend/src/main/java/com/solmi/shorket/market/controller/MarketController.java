package com.solmi.shorket.market.controller;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.ListMarketResponseDto;
import com.solmi.shorket.market.dto.MarketListResponseDto;
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
            notes = "`sort`, `date`는 정수 인덱스(0, 1, 2, ...) 또는 문자열로 넘겨주시면 됩니다. **문자열로 작성할 경우, 반드시!! 전부 대문자로 작성해야합니다**.\n\n" +
                    "- `sort`: VIEW(조회순, 0), INTEREST(관심순, 1), LATEST(최신순, 2), DICT(가나다순, 3)\n\n" +
                    "- `locals`: 검색할 지역 목록이 담긴 배열. **빈 배열로 요청할 경우 모든 지역으로 검색** (ex. `[\"서울\", \"경기도\"]`)\n\n" +
                    "- `date`: UPCOMING(진행 예정, 0), CURRENT(진행 중, 1), COMPLETE(종료, 2)"
    )
    @GetMapping("")
    public MarketListResponseDto<List<ListMarketResponseDto>> getMarkets(@RequestBody SortingAndFilteringInfo info) {
        List<Market> markets = marketService.findMarkets(info);
        List<ListMarketResponseDto> res = markets.stream()
                .map(ListMarketResponseDto::new)
                .collect(Collectors.toList());
        return new MarketListResponseDto<>(res);
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