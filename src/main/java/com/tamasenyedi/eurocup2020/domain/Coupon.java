package com.tamasenyedi.eurocup2020.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private boolean redeemed;
    private boolean winner;
    private Date redeemTime;

    @Enumerated(value = EnumType.STRING)
    private Territory territory;

    @ManyToOne
    private User redeemer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Date getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(Date redeemTime) {
        this.redeemTime = redeemTime;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public User getRedeemer() {
        return redeemer;
    }

    public void setRedeemer(User redeemer) {
        this.redeemer = redeemer;
    }
}
