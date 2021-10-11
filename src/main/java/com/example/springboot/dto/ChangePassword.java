package com.example.springboot.dto;

import lombok.Data;

@Data
public class ChangePassword {
    private String username;
    private String oldPassword;
    private String newPassword;
}
