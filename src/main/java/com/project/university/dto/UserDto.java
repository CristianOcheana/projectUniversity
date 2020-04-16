package com.project.university.dto;


import com.project.university.validation.PasswordMatches;
import com.project.university.validation.ValidPassword;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@PasswordMatches
public class UserDto {

    @Id
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @NotEmpty(message = "First Name is mandatory")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 20)
    @NotEmpty(message = "Last Name is mandatory")
    private String lastName;

    @Email
    @NotNull
    @NotEmpty(message = "Email is mandatory")
    private String email;

    @NotNull
    @NotEmpty(message = "Password is mandatory")
    @ValidPassword
    private String password;

    @NotNull
    @NotEmpty(message = "Password confirmation is required")
    private String matchingPassword;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

}
