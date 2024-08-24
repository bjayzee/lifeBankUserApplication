package com.lifebank.service;

import com.lifebank.dto.LoginDto;
import com.lifebank.model.LifeBankUser;
import com.lifebank.model.UserRepository;
import com.lifebank.util.LifeBankException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserServiceInterface{

    private final UserRepository userRepository;
    private final JWTService jwtService;

    private final AuthenticationManager authManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @Override
    public LifeBankUser createUser(String name, String email, String password) {
        if(userRepository.existsByEmail(email)){
            throw new LifeBankException("user already exist");
        }
        return userRepository.save(LifeBankUser.builder().email(email).name(name).password(encoder.encode(password)).build());
    }

    @Override
    public List<LifeBankUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<LifeBankUser> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public List<LifeBankUser> getAllUsersSortedByName() {
        return userRepository.findAllByOrderByNameAsc();
    }

    public String verify(LoginDto userDto) {

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDto.email());
        } else {
            return "fail";
        }
    }
}
