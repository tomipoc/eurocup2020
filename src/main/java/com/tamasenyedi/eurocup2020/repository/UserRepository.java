package com.tamasenyedi.eurocup2020.repository;

import com.tamasenyedi.eurocup2020.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository supports pagination
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
