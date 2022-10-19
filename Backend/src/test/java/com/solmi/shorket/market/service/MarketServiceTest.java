package com.solmi.shorket.market.service;

import com.solmi.shorket.global.exception.MarketNotFoundException;
import com.solmi.shorket.global.exception.MarketUnauthorizedException;
import com.solmi.shorket.market.domain.Address;
import com.solmi.shorket.market.domain.Market;
import com.solmi.shorket.market.dto.CreateMarketRequestDto;
import com.solmi.shorket.market.dto.UpdateMarketRequestDto;
import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.dto.UserSignupRequestDto;
import com.solmi.shorket.user.service.SecurityService;
import com.solmi.shorket.user.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MarketServiceTest {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;
    @Autowired
    private MarketService marketService;

    @Test
    @DisplayName("Market 등록")
    public void saveMarket() {
        // given
        User user = createMockUser(1);
        Market market = createMockMarket(user, 1);

        // when
        Market findMarket = marketService.findMarket(market.getIdx());

        // then
        assertThat(findMarket).isEqualTo(market);
    }

    @Test
    @DisplayName("Market 수정")
    public void updateMarket() {
        // given
        User user = createMockUser(1);
        Market market = createMockMarket(user, 1);

        // when
        marketService.updateMarket(user, market.getIdx(), new UpdateMarketRequestDto(
                "마켓", "설명", new Address("경기", "수원", "상세주소"),
                LocalDateTime.of(2021, 1, 1, 0, 0), LocalDateTime.now(),
                List.of("id=url"), "id=mapUrl")
        );

        // then
        assertThat(market.getAddress().getSido()).isEqualTo("경기");
        assertThat(market.getAddress().getSigungu()).isEqualTo("수원");
        assertThat(market.getStartDate()).isBefore(LocalDateTime.of(2022, 1, 1, 0, 0));
        assertThat(market.getImages().size()).isEqualTo(1);
    }

    @Test(expected = MarketUnauthorizedException.class)
    @DisplayName("Market 수정 권한 예외")
    public void updateMarketUnauthorized() {
        // given
        User user1 = createMockUser(1);
        User user2 = createMockUser(2);
        Market market = createMockMarket(user1, 1);

        // when
        marketService.updateMarket(user2, market.getIdx(), new UpdateMarketRequestDto(
                "마켓", "설명", new Address("경기", "수원", "상세주소"),
                LocalDateTime.of(2021, 1, 1, 0, 0), LocalDateTime.now(),
                List.of("id=url"), "id=mapUrl")
        );

        // then
        fail("Market 수정 권한이 없으므로 예외가 발생해야 한다.");
    }

    @Test(expected = MarketNotFoundException.class)
    @DisplayName("Market 삭제")
    public void deleteMarket() {
        // given
        User user = createMockUser(1);
        Market market = createMockMarket(user, 1);
        int idx = market.getIdx();

        // when
        marketService.deleteMarket(user, idx);
        marketService.findMarket(idx);

        // then
        fail("삭제된 마켓을 조회하였으므로 예외가 발생한다.");
    }

    @Test(expected = MarketUnauthorizedException.class)
    @DisplayName("Market 삭제 권한 예외")
    public void deleteMarketUnauthorized() {
        // given
        User user1 = createMockUser(1);
        User user2 = createMockUser(2);
        Market market = createMockMarket(user1, 1);

        // when
        marketService.deleteMarket(user2, market.getIdx());

        // then
        fail("Market 삭제 권한이 없으므로 예외가 발생한다.");
    }

    private User createMockUser(int n) {
        UserSignupRequestDto signupRequestDto = UserSignupRequestDto.builder()
                .email("test" + n + "@gmail.com")
                .password("0000")
                .name("Test" + n)
                .nickName("TestNickname" + n)
                .loginType(LoginType.E)
                .build();

        Integer userIdx = securityService.signup(signupRequestDto);
        return userService.findByIdx(userIdx);
    }

    private Market createMockMarket(User manager, int n) {
        return marketService.saveMarket(
                new CreateMarketRequestDto(
                        "", "마켓" + n, "설명", new Address("서울", "강남", "상세주소"),
                        LocalDateTime.of(2022, 1, 1, 0, 0), LocalDateTime.now(),
                        List.of("id=url1", "id=url2", "id=url3"), "id=mapUrl").toEntity(manager)
        );
    }
}