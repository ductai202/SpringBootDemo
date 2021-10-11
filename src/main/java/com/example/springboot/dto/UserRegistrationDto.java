package com.example.springboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegistrationDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
