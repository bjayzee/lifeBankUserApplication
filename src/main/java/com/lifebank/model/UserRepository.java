package com.lifebank.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<LifeBankUser, Integer> {

    List<LifeBankUser> findAllByOrderByNameAsc();
    LifeBankUser findByEmail(String email);

    Boolean existsByEmail(String email);
}
