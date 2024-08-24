package com.lifebank.service;

import com.lifebank.model.LifeBankUser;
import com.lifebank.model.UserPrincipal;
import com.lifebank.model.UserRepository;
import com.lifebank.util.LifeBankException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LifeBankUserDetailService implements UserDetailsService {
    private final UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email){
        LifeBankUser user = userRepo.findByEmail(email).orElseThrow(() -> new LifeBankException("User not found"));
        return new UserPrincipal(user);
    }
}
