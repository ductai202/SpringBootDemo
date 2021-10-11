package com.example.springboot.dao.repository;

import com.example.springboot.dao.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String userName);
    User findByEmail(String email);
    User findByResetPasswordToken(String token);
    @Query(nativeQuery = true, value =  "SELECT reset_password_token FROM users where email = :email ")
    String findTokenByEmail(String email);

}