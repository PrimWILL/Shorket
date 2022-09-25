package com.solmi.shorket.market.controller;

import com.solmi.shorket.booth.dto.BoothDto;
import com.solmi.shorket.booth.service.BoothService;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.*;
import com.solmi.shorket.market.service.MarketService;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.service.MarketInterestService;
import com.solmi.shorket.user.service.SecurityService;
import com.solmi.shorket.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "Markets", value = "Market API. Size of page: 12")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/markets")
public class MarketController {

    private final UserService userService;
    private final SecurityService securityService;
    private final MarketService marketService;
    private final MarketInterestService marketInterestService;
    private final BoothService boothService;

    @Value("${number-of-market-paging}")
    private int numberOfPaging;

    @ApiOperation(
            value = "Market 등록",
            notes = "등록할 market의 정보를 받아 새로운 market을 생성합니다."
    )
    @PostMapping
    public CreateMarketResponseDto saveMarket(@RequestBody @Valid CreateMarketRequestDto requestDto) {
        User manager = userService.findByEmail(requestDto.getUserEmail());
        Market market = requestDto.toEntity(manager);
        return new CreateMarketResponseDto(marketService.saveMarket(market).getIdx());
    }

    @ApiOperation(
            value = "Market 목록 조회",
            notes = "- `sort`: VIEW(조회순, 0), INTEREST(관심순, 1), LATEST(최신순, 2), DICT(가나다순, 3)\n\n" +
                    "- `locals`: 검색할 지역 목록이 담긴 배열. `locals`를 보내지 않거나 비워서 보내면 지역 필터링 없이 조회합니다.\n\n" +
                    "- `date`: UPCOMING(진행 예정, 0), CURRENT(진행 중, 1), COMPLETE(종료, 2)\n\n" +
                    "- `page`: 조회할 페이지 번호 (0부터 시작)\n\n" +
                    "**`sort`, `date`는 전부 대문자로 작성해주세요.**"
    )
    @GetMapping
    public MarketListResponseDto<List<ListMarketResponseDto>> getMarkets(@RequestParam MarketSortingCriteria sort,
                                                                         @RequestParam MarketFilteringCriteriaByDate date,
                                                                         @RequestParam(defaultValue = "") List<String> locals,
                                                                         @RequestParam(defaultValue = "0") Integer page) {
        List<ListMarketResponseDto> res = marketService.findMarkets(sort, date, locals, page).stream()
                .map(ListMarketResponseDto::new)
                .collect(Collectors.toList());
        return new MarketListResponseDto<>(res);
    }

    @ApiOperation(
            value = "로그인 사용자가 관리하는 Market 목록 조회",
            notes = "- `sort`: VIEW(조회순, 0), INTEREST(관심순, 1), LATEST(최신순, 2), DICT(가나다순, 3)\n\n" +
                    "- `page`: 조회할 페이지 번호 (0부터 시작)\n\n" +
                    "**`sort`는 전부 대문자로 작성해주세요.**"
    )
    @GetMapping("/management")
    public MarketListResponseDto<List<ListMarketResponseDto>> getManagedMarkets(@RequestHeader("X-AUTH-TOKEN") String token,
                                                                                @RequestParam(defaultValue = "LATEST") MarketSortingCriteria sort,
                                                                                @RequestParam(defaultValue = "0") Integer page) {
        User user = securityService.findUserByAccessToken(token);
        List<ListMarketResponseDto> res = marketService.findManagedMarkets(user, sort, page).stream()
                .map(ListMarketResponseDto::new)
                .collect(Collectors.toList());
        return new MarketListResponseDto<>(res);
    }

    @ApiOperation(
            value = "관심 Market 목록 조회",
            notes = "로그인 user의 관심 Market 목록을 조회한다."
    )
    @GetMapping("/interest")
    public MarketListResponseDto getInterestMarkets(@RequestHeader("X-AUTH-TOKEN") String token,
                                                    @RequestParam(defaultValue = "LATEST") MarketSortingCriteria sort,
                                                    @RequestParam(defaultValue = "0") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, numberOfPaging, sort.toSort());
        User user = securityService.findUserByAccessToken(token);
        List<Market> interestMarkets = marketService.getInterestMarkets(pageRequest, user);
        return new MarketListResponseDto(
                interestMarkets.stream()
                        .map(ListMarketResponseDto::new)
                        .collect(Collectors.toList())
        );
    }

    @ApiOperation(
            value = "Market 상세 정보 조회",
            notes = "`marketIdx`에 해당하는 market의 상세 정보 조회."
    )
    @GetMapping("/{marketIdx}")
    public MarketResponseDto getMarket(@PathVariable Integer marketIdx) {
        Market market = marketService.findMarket(marketIdx);
        return new MarketResponseDto(market);
    }

    @ApiOperation(
            value = "Market 수정",
            notes = "수정 사항을 받아서 `marketIdx`에 해당하는 market의 상세 정보 조회."
    )
    @PutMapping("/{marketIdx}")
    public void updateMarket(@RequestHeader("X-AUTH-TOKEN") String token,
                             @PathVariable Integer marketIdx,
                             @RequestBody @Valid UpdateMarketRequestDto requestDto) {
        User user = securityService.findUserByAccessToken(token);
        marketService.updateMarket(user, marketIdx, requestDto);
    }

    @ApiOperation(
            value = "Market 삭제",
            notes = "`marketIdx`에 해당하는 market을 삭제합니다."
    )
    @DeleteMapping("/{marketIdx}")
    public void deleteMarket(@RequestHeader("X-AUTH-TOKEN") String token,
                             @PathVariable Integer marketIdx) {
        User user = securityService.findUserByAccessToken(token);
        marketService.deleteMarket(user, marketIdx);
    }

    @ApiOperation(
            value = "관심 Market 추가",
            notes = "`X-AUTH-TOKEN`에 해당하는 user가 `marketIdx`에 해당하는 market을 관심 market으로 추가한다."
    )
    @PostMapping("/{marketIdx}/like")
    public void addMarketInterest(@RequestHeader("X-AUTH-TOKEN") String token, @PathVariable Integer marketIdx) {
        User user = securityService.findUserByAccessToken(token);

        marketInterestService.addInterest(user, marketIdx);
    }

    @ApiOperation(
            value = "관심 Market 취소",
            notes = "`X-AUTH-TOKEN`에 해당하는 user의 관심 market 목록에서 `marketIdx`에 해당하는 market을 삭제한다."
    )
    @DeleteMapping("/{marketIdx}/like")
    public void cancelMarketInterest(@RequestHeader("X-AUTH-TOKEN") String token, @PathVariable Integer marketIdx) {
        User user = securityService.findUserByAccessToken(token);

        marketInterestService.cancelInterest(user, marketIdx);
    }

    @ApiOperation(
            value = "Market 부스 목록 조회",
            notes = "부스 목록을 불러온다."
    )
    @GetMapping("/{marketIdx}/booths")
    public Page<BoothDto> getBoothsByMarket(
            @PageableDefault(size = 10) Pageable pageable,
            @PathVariable Integer marketIdx
    ) {
        return boothService.getBoothsByMarket(pageable, marketIdx);
    }
}