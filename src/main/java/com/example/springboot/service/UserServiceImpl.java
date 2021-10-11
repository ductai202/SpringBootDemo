package com.example.springboot.service;

import com.example.springboot.dao.User.Role;
import com.example.springboot.dao.User.User;
import com.example.springboot.dao.exception.InvalidOldPasswordException;
import com.example.springboot.dao.exception.NotAuthenticatedException;
import com.example.springboot.dao.exception.UserNotFoundException;
import com.example.springboot.dao.repository.RoleRepository;
import com.example.springboot.dao.repository.UserRepository;
import com.example.springboot.dto.UserRegistrationDto;
import jdk.internal.org.jline.utils.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database {} ",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        String created_name = userRepository.findByUsername(username).getName();
        user.setCreated_by(created_name);
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }
    @Override
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any user with the email " + email);
        }
    }
    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
    @Override
    public void changeUserPassword(String username, String oldPass, String newPass) {
        User user = userRepository.findByUsername(username);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !authentication.getName().equals(username)){
            throw new NotAuthenticatedException("You can't change the password !");
        }
        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new InvalidOldPasswordException("The old password you have entered is incorrect !");
        }
        if (!newPass.equals(oldPass)) {
            throw new InvalidOldPasswordException("New Password Cannot Be the Same as Old Password");
        }
        user.setPassword(passwordEncoder.encode(newPass));
//        if(authentication!=null && authentication.isAuthenticated() && authentication.getName().equals(username)) {
//            if (passwordEncoder.matches(oldPass, user.getPassword())){
//                user.setPassword(passwordEncoder.encode(newPass));
//                if(newPass.equals(oldPass)){
//                    throw new InvalidOldPasswordException("New Password Cannot Be the Same as Old Password");
//                }
//            }
//            else throw new InvalidOldPasswordException("The old password you have entered is incorrect !");
//        }
//        else throw new NotAuthenticatedException("You can't change the password !");


        userRepository.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {} ", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }


}
