package com.example.workshop.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginDto {

    @Size(min = 3, max = 30, message = "Enter valid username")
    private String username;
    @Pattern(regexp = "[A-Za-z\\d]{6,}" , message = "Enter valid password")
    private String password;

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
