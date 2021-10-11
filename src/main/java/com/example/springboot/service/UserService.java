package com.example.springboot.service;

import com.example.springboot.dao.User.Role;
import com.example.springboot.dao.User.User;
import com.example.springboot.dao.exception.UserNotFoundException;
import com.example.springboot.dto.UserRegistrationDto;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void updateResetPasswordToken(String token, String email) throws UserNotFoundException;
    User getByResetPasswordToken(String token);
    void updatePassword(User user, String newPassword);
    void changeUserPassword(String username, String oldPass, String newPass);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
