package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.consts.WinServiceConsts;
import com.tamasenyedi.eurocup2020.domain.Coupon;
import com.tamasenyedi.eurocup2020.domain.Territory;
import com.tamasenyedi.eurocup2020.domain.User;
import com.tamasenyedi.eurocup2020.dto.BasicResponseDto;
import com.tamasenyedi.eurocup2020.dto.ServiceResponseDto;
import com.tamasenyedi.eurocup2020.repository.CouponRepository;
import com.tamasenyedi.eurocup2020.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WinnerServiceImplSpecificTest {

    WinnerServiceImpl winnerService;

    int huStart = 39;
    int deStart = 79;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private EntityCreator entityCreator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        winnerService = new WinnerServiceImpl(userRepository, couponRepository, entityCreator, huStart, deStart);
    }

    @Test
    void verifyWinnerDE() {
        String email = "test@test.com";
        int age = 20;
        String couponCode = "D45fv3r5tz";
        String territory = "DE";

        ServiceResponseDto<BasicResponseDto> dto = doVerify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.COUPON_WIN + couponCode, dto.getDataDto().getStatus_text());
    }

    @Test
    void verifyWinnerHU() {
        String email = "test@test.com";
        int age = 20;
        String couponCode = "D45fv3r5tz";
        String territory = "DE";

        ServiceResponseDto<BasicResponseDto> dto = doVerify(email, age, couponCode, territory);
        assertEquals(WinServiceConsts.COUPON_WIN + couponCode, dto.getDataDto().getStatus_text());
    }

    @Test
    void verifyTryAgain() {
        String email = "test@test.com";
        int age = 20;
        String couponCode = "D45fv3r5tz";
        String couponCode2 = "D45fv3r5tT";
        String territory = "HU";

        ServiceResponseDto<BasicResponseDto> dtoUnused = doVerify(email, age, couponCode, territory);
        ServiceResponseDto<BasicResponseDto> dto2 = doVerify(email, age, couponCode2, territory);
        assertEquals(WinServiceConsts.COUPON_TRY_AGAIN + couponCode2, dto2.getDataDto().getStatus_text());
    }

    @Test
    void doSeveralUserAttemptsLoseHU() {
        doSeveralUserAttemptsLose("HU", huStart, WinServiceConsts.HU_FREQ, 1000);
    }

    @Test
    void doSeveralUserAttemptsLoseDE() {
        doSeveralUserAttemptsLose("DE", deStart, WinServiceConsts.DE_FREQ, 1000);
    }

    /**
     * Helper methods
     * */

    private void doSeveralUserAttemptsLose(String territory, int startId, int freq, int targetCount) {
        String email = "test@test.com";
        int age = 20;
        Set<String> couponCodes = generateCouponCodes(targetCount);

        int starterId = startId;
        for(String couponCode : couponCodes) {
            starterId++;
            ServiceResponseDto<BasicResponseDto> dto = doVerify(email, age, couponCode, territory);

            if(starterId % freq == 0) {
                assertEquals(WinServiceConsts.COUPON_WIN + couponCode, dto.getDataDto().getStatus_text());
            }
            else {
                assertEquals(WinServiceConsts.COUPON_TRY_AGAIN + couponCode, dto.getDataDto().getStatus_text());
            }
        }
    }

    public Set<String> generateCouponCodes(int targetCount) {
        Set<String> generatedCouponCodes = new HashSet<>(targetCount);
        Random random = new Random();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int length = 10; // couponCode length

        while(generatedCouponCodes.size() < targetCount) {
            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(length)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            generatedCouponCodes.add(generatedString);
        }
        return generatedCouponCodes;
    }

    public ServiceResponseDto<BasicResponseDto> doVerify(String email, int age, String couponCode, String territory) {
        User user = createUser(email, age);
        when(entityCreator.createUser(email, age)).thenReturn(user);
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.ofNullable(user));

        Coupon coupon = createCoupon(couponCode, territory);
        when(entityCreator.createCoupon(couponCode, territory)).thenReturn(coupon);

        ServiceResponseDto<BasicResponseDto> dto = winnerService.verify(email, age, couponCode, territory);
        return dto;
    }

    public User createUser(String email, int age) {
        User user = new User();
        user.setEmail(email);
        user.setAge(age);
        return user;
    }

    public Coupon createCoupon(String couponCode, String territory) {
        Coupon coupon = new Coupon();
        coupon.setCode(couponCode);
        coupon.setRedeemTime(new Date());
        coupon.setTerritory(Territory.valueOf(territory));
        coupon.setRedeemed(true);
        return coupon;
    }
}