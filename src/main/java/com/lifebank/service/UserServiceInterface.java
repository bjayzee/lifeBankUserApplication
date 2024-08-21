package com.lifebank.service;

import com.lifebank.model.LifeBankUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserServiceInterface {


    LifeBankUser createUser(String name, String email, String password);

    List<LifeBankUser> getAllUsers();

    Page<LifeBankUser> getAllUsers(int page, int size);

    List<LifeBankUser> getAllUsersSortedByName();
}
