package com.lifebank.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto{
    @NotBlank(message = "name can not be blank")
    private String name;

    @Email(message = "invalid email")
    @NotBlank(message = "email cannot be null")
    private String email;

    private String password;


}
