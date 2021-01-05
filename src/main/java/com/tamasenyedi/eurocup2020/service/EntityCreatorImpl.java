package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.domain.Coupon;
import com.tamasenyedi.eurocup2020.domain.Territory;
import com.tamasenyedi.eurocup2020.domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EntityCreatorImpl implements EntityCreator {

    @Override
    public User createUser(String email, int age) {
        User u = new User();
        u.setEmail(email);
        u.setAge(age);
        return u;
    }

    @Override
    public Coupon createCoupon(String couponCode, String territory) {
        Coupon c = new Coupon();
        c.setCode(couponCode);
        c.setRedeemTime(new Date());
        c.setTerritory(Territory.valueOf(territory));
        c.setRedeemed(true);
        //c.setWinner(didItWin);
        return c;
    }
}
