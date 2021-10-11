package com.example.springboot.dao.User;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.example.springboot.dao.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "created_by")
    private String created_by;

    public User(Long id, @NotEmpty String name, @NotEmpty String username,@NotEmpty String email, @NotEmpty String password, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();


}
