package com.example.springboot;
import com.example.springboot.dao.User.Role;
import com.example.springboot.dao.User.User;
import com.example.springboot.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.saveRole(new Role(1L,"Role_User"));
//            userService.saveRole(new Role(2L,"Role_Admin"));
//
//            userService.saveUser(new User(3L, "Long","long","1234" ,new ArrayList<>()));
//            userService.saveUser(new User(4L, "Tân","tan","1234",new ArrayList<>()));
//            userService.saveUser(new User(5L, "Chung","chung","1234",new ArrayList<>()));
//
//            userService.addRoleToUser("long","Role_User");
//            userService.addRoleToUser("tan","Role_User");
//            userService.addRoleToUser("tan","Role_Admin");
//            userService.addRoleToUser("chung","Role_Admin");
//        };
//    }

}
