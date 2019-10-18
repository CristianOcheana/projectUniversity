package com.project.university.entities;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public User() {

    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}


