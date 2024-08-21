package com.lifebank.service;

import com.lifebank.model.LifeBankUser;
import com.lifebank.model.UserPrincipal;
import com.lifebank.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RequiredArgsConstructor
public class LifeBankUserDetailService implements UserDetailsService {
    private final UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LifeBankUser user = userRepo.findByEmail(email);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
