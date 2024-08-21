package com.lifebank.controller;

import com.lifebank.dto.UserDto;
import com.lifebank.service.UserServiceImplementation;
import com.lifebank.util.LifeBankException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImplementation userService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto.getName(), userDto.getEmail(), userDto.getPassword()));
        }catch (LifeBankException | NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size){
        try{
            return ResponseEntity.ok().body(userService.getAllUsers(page, size));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/users/sort")
    public ResponseEntity<?> getSortedUsers(){
        try{
            return ResponseEntity.ok().body(userService.getAllUsersSortedByName());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
