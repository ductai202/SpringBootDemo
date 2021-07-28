package com.example.springboot.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "semester_subject",
    joinColumns = @JoinColumn(name = "semester_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id",referencedColumnName = "id"))
    private List<Subject> subjects;

}
