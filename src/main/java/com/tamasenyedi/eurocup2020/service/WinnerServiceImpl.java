package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.consts.WinServiceConsts;
import com.tamasenyedi.eurocup2020.domain.Coupon;
import com.tamasenyedi.eurocup2020.domain.Territory;
import com.tamasenyedi.eurocup2020.domain.User;
import com.tamasenyedi.eurocup2020.dto.BasicResponseDto;
import com.tamasenyedi.eurocup2020.dto.ServiceResponseDto;
import com.tamasenyedi.eurocup2020.helper.response.ResponseCode;
import com.tamasenyedi.eurocup2020.repository.CouponRepository;
import com.tamasenyedi.eurocup2020.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WinnerServiceImpl implements WinnerService {

    // TODO make these 2 to private
    public AtomicInteger deCounter;
    public AtomicInteger huCounter;

    private UserRepository userRepository;
    private CouponRepository couponRepository;
    private EntityCreator entityCreator;

    @Autowired
    public WinnerServiceImpl(UserRepository userRepository, CouponRepository couponRepository, EntityCreator entityCreator) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
        this.entityCreator = entityCreator;

        // ugly for now :(
        deCounter = new AtomicInteger(couponRepository.countByTerritory(Territory.DE));
        huCounter = new AtomicInteger(couponRepository.countByTerritory(Territory.HU));
    }

    public WinnerServiceImpl(UserRepository userRepository, CouponRepository couponRepository, EntityCreator entityCreator, int huStart, int deStart) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
        this.entityCreator = entityCreator;

        deCounter = new AtomicInteger(deStart);
        huCounter = new AtomicInteger(huStart);
    }

    @Override
    public ServiceResponseDto<BasicResponseDto> verify(String email, int age, String couponCode, String territory) {
        BasicResponseDto responseDto = new BasicResponseDto();


        Optional<Coupon> couponOptional = couponRepository.findByCode(couponCode);

        if( validateParams(email, territory) ) {
            responseDto.statusFAIL(WinServiceConsts.VERIFY_ISSUE);
        }
        else if (!isValidCoupon(couponCode)) {
            responseDto.statusFAIL(WinServiceConsts.VERIFY_COUPON_NOT_VALID + couponCode);
        }
        else if(age < WinServiceConsts.AGE_LIMIT) {
            responseDto.statusFAIL(WinServiceConsts.AGE_LIMIT_STATUS_TEXT);
        }
        // is coupon already redeemed
        else if (couponOptional.isPresent() && couponOptional.get().isRedeemed() ) {
            responseDto.statusFAIL(WinServiceConsts.VERIFY_COUPON_USED + couponCode);
        }
        else {
            // all params should be valid
            boolean didItWin = handleWin(territory);

            User u = null;
            Optional<User> userOptional = userRepository.findByEmail(email);
            if(userOptional.isPresent()) {
                u = userOptional.get();
            }
            else {
                u = entityCreator.createUser(email, age);
            }

            Coupon c = entityCreator.createCoupon(couponCode, territory);
            c.setWinner(didItWin);
            u.redeemCoupon(c);

            userRepository.save(u);
            couponRepository.save(c);

            if (didItWin) {
                responseDto.statusOK(WinServiceConsts.COUPON_WIN + couponCode);
            }
            else {
                responseDto.statusFAIL(WinServiceConsts.COUPON_TRY_AGAIN + couponCode);
            }
        }
        return new ServiceResponseDto<>(responseDto, ResponseCode.SUCCESS);
    }

    private boolean validateParams(String email, String territory) {
        Territory userTerritory = Territory.asTerritory(territory);
        boolean issue = userTerritory == null || email == null || email.isEmpty();
        return issue;
    }

    private boolean isValidCoupon(String couponCode) {
        boolean isValid = couponCode != null && couponCode.length() == WinServiceConsts.COUPON_LENGTH;
        return isValid;
    }

    private boolean handleWin(String territory) {
        Territory t = Territory.asTerritory(territory.toUpperCase());
        if (t == Territory.DE) {
            int currentCnt = deCounter.incrementAndGet();
            return calculateForHandleWin(currentCnt, WinServiceConsts.DE_FREQ, WinServiceConsts.DE_DAILY, WinServiceConsts.DE_MAX);
        }
        else if (t == Territory.HU) {
            int currentCnt = huCounter.incrementAndGet();
            return calculateForHandleWin(currentCnt, WinServiceConsts.HU_FREQ, WinServiceConsts.HU_DAILY, WinServiceConsts.HU_MAX);
        }
        return false;
    }

    private boolean calculateForHandleWin(int currentCnt, int freq, int daily, int max) {
        if(currentCnt > 0 && currentCnt % freq == 0 && currentCnt < max) {
            return true;
        }
        // TODO Daily, Max
        return false;
    }
}
