package com.tamasenyedi.eurocup2020.consts;

public class WinServiceConsts {
    public static final int COUPON_LENGTH = 10;

    public static final int DE_MAX = 10000;
    public static final int DE_FREQ = 40;
    public static final int DE_DAILY = 250; // TODO

    public static final int HU_MAX = 5000;
    public static final int HU_FREQ = 80;
    public static final int HU_DAILY = 100; // TODO

    public static final int AGE_LIMIT = 13;
    public static final String AGE_LIMIT_STATUS_TEXT = "You can not play under the age of " + AGE_LIMIT;
    public static final String VERIFY_ISSUE = "Please check if all your data is provided (email, age, territory)!";
    public static final String VERIFY_COUPON_ISSUE = "Please check your coupon code: ";
    public static final String VERIFY_COUPON_NOT_VALID = "Coupon code is not valid! Please check your coupon code: ";
    public static final String VERIFY_COUPON_USED = "The coupon is aldeady used: ";

    public static final String COUPON_WIN = "WINNER! Coupon code: ";
    public static final String COUPON_TRY_AGAIN = "You did not win this time. Please try it again. Coupon code: ";

}
