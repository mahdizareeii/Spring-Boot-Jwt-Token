package com.test.jwt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Email can not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password can not be blank")
    @Size(min = 4, message = "Password must be at least 6 characters long")
    /*@Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$",
            message = "Password must contain at least one letter and one number"
    )*/
    private String password;

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
}
