package com.tamasenyedi.eurocup2020.repository;

import com.tamasenyedi.eurocup2020.domain.Coupon;
import com.tamasenyedi.eurocup2020.domain.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository supports pagination
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCode(String code);

    int countByTerritory(Territory territory);
}
