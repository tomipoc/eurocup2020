package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.consts.WinServiceConsts;
import com.tamasenyedi.eurocup2020.dto.BasicResponseDto;
import com.tamasenyedi.eurocup2020.dto.ServiceResponseDto;
import com.tamasenyedi.eurocup2020.repository.CouponRepository;
import com.tamasenyedi.eurocup2020.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WinnerServiceImplSpecificTest {

    WinnerServiceImpl winnerService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private EntityCreator entityCreator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        winnerService = new WinnerServiceImpl(userRepository, couponRepository, entityCreator, 39, 79);
    }

    @Test
    void verifyWinnerDE() {
        String email = "test@test.com";
        int age = 20;
        String couponCode = "D45fv3r5tz";
        String territory = "DE";
        ServiceResponseDto<BasicResponseDto> dto = winnerService.verify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.AGE_LIMIT_STATUS_TEXT, dto.getDataDto().getStatus_text());
    }

    @Test
    void verifyWinnerHU() {
        String email = "test@test.com";
        int age = 20;
        String couponCode = "D45fv3r5tz";
        String territory = "HU";
        ServiceResponseDto<BasicResponseDto> dto = winnerService.verify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.COUPON_WIN + couponCode, dto.getDataDto().getStatus_text());
    }

    @Test
    void verifyTryAgain() {
        String email = "test@test.com";
        int age = 20;
        String couponCode = "D45fv3r5tz";
        String territory = "HU";
        ServiceResponseDto<BasicResponseDto> dto = winnerService.verify(email, age, couponCode, territory);

        couponCode = "D45fv3r5tT";
        dto = winnerService.verify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.COUPON_TRY_AGAIN + couponCode, dto.getDataDto().getStatus_text());
    }
}