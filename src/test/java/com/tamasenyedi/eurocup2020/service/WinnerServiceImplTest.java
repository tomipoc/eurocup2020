package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.consts.WinServiceConsts;
import com.tamasenyedi.eurocup2020.domain.User;
import com.tamasenyedi.eurocup2020.dto.BasicResponseDto;
import com.tamasenyedi.eurocup2020.dto.ServiceResponseDto;
import com.tamasenyedi.eurocup2020.repository.CouponRepository;
import com.tamasenyedi.eurocup2020.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class WinnerServiceImplTest {

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
        winnerService = new WinnerServiceImpl(userRepository, couponRepository, entityCreator);
    }

    @Test
    void verifyEmailTerritoryFail() {
        String email = "";
        int age = 20;
        String couponCode = "";
        String territory = "";
        ServiceResponseDto<BasicResponseDto> dto = winnerService.verify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.VERIFY_ISSUE, dto.getDataDto().getStatus_text());
    }

    @Test
    void verifyCouponValidityFail() {
        String email = "test@test.com";
        int age = 20;
        String couponCode = "D45fv";
        String territory = "HU";
        ServiceResponseDto<BasicResponseDto> dto = winnerService.verify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.VERIFY_COUPON_NOT_VALID + couponCode, dto.getDataDto().getStatus_text());
    }

    @Test
    void verifyAgeLimitFail() {
        String email = "test@test.com";
        int age = 2;
        String couponCode = "D45fv3r5tz";
        String territory = "HU";
        ServiceResponseDto<BasicResponseDto> dto = winnerService.verify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.AGE_LIMIT_STATUS_TEXT, dto.getDataDto().getStatus_text());
    }

//    @Test
//    void verifyIsPresent() {
//
//    }

    @Test
    void verifyWinnerHU() {
        String email = "test@test.com";
        int age = 2;
        String couponCode = "D45fv3r5tz";
        String territory = "HU";

        User u = new User();
        u.setEmail(email);
        u.setAge(age);

        ServiceResponseDto<BasicResponseDto> dto = winnerService.verify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.AGE_LIMIT_STATUS_TEXT, dto.getDataDto().getStatus_text());
    }

    @Test
    void verifyTryAgain() {

    }
}