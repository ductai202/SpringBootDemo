package com.example.springboot.dao.entity;

import com.example.springboot.dao.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Long age;

    @Column(name ="created_by")
    private String created_by;

    @Column(name = "updated_by")
    private String updated_by;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "users_student",
//            joinColumns =
//                    { @JoinColumn(name = "student_id", referencedColumnName = "id") },
//            inverseJoinColumns =
//                    { @JoinColumn(name = "user_id", referencedColumnName = "id") })
//    private User user;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
