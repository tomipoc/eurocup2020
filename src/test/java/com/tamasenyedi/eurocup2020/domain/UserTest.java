package com.tamasenyedi.eurocup2020.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    void getId() {
        Long idValue = 4L;
        user.setId(idValue);
        assertEquals(idValue, user.getId());
    }

    @Test
    void getEmail() {
    }

    @Test
    void getRedeemedCoupons() {
    }
}