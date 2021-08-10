package com.example.workshop.service;

import com.example.workshop.models.dto.UserLoginDto;
import com.example.workshop.models.dto.UserRegisterDto;
import com.example.workshop.models.entities.User;

public interface UserService {

    void loginUser(UserLoginDto userLoginDto);

    void registerUser(UserRegisterDto userRegisterDto);

    User getLoggedUser();

    void logout();
}
