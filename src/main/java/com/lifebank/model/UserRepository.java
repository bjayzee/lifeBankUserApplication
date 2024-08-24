package com.lifebank.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<LifeBankUser, Integer> {

    List<LifeBankUser> findAllByOrderByNameAsc();
    Optional<LifeBankUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}
