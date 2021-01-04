package com.tamasenyedi.eurocup2020.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Should be a valid email.")
    @Column(unique=true)
    private String email;

    private int age;

    @OneToMany(mappedBy = "redeemer")
    private final Set<Coupon> redeemedCoupons = new HashSet<>();

    public User redeemCoupon(Coupon coupon) {
        coupon.setRedeemer(this);
        this.redeemedCoupons.add(coupon);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Coupon> getRedeemedCoupons() {
        return redeemedCoupons;
    }
}
