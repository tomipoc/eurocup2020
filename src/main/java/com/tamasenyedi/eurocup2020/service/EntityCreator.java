package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.domain.Coupon;
import com.tamasenyedi.eurocup2020.domain.User;

public interface EntityCreator {
    User createUser(String email, int age);

    Coupon createCoupon(String couponCode, String territory);
}
