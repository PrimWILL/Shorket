package com.solmi.shorket.market.controller;

import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.ListMarketResponseDto;
import com.solmi.shorket.market.dto.MarketReponseDto;
import com.solmi.shorket.market.service.MarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import static org.springframework.data.domain.Sort.Direction.*;

@Api(tags = "Markets")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/markets")
public class MarketController {

    private final MarketService marketService;


    @ApiOperation(
            value = "Market 목록 조회",
            notes = "(추후 내용 다시 작성할게요) 기본 정렬 기준: 조회수(관심순으로 변경할 예정)"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "sort",
                    value = "정렬 기준",
                    required = true,
                    dataType = "String",
                    paramType = "query",
                    defaultValue = "viewCount"
            )
    })
    @GetMapping("")
    public Page<ListMarketResponseDto> getMarkets(@ApiIgnore @PageableDefault(sort = "viewCount", direction = DESC) Pageable pageable) {
        return marketService.findMarkets(pageable)
                .map(ListMarketResponseDto::new);
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