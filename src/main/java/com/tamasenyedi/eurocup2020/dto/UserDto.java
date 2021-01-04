package com.tamasenyedi.eurocup2020.dto;

import lombok.*;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto extends AbstractDto {
    private String email;
    private int age;
    private String coupon;
    private String territory;

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getTerritory() {
        return territory != null ? territory.toUpperCase() : null;
    }
}
