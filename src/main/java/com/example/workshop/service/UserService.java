package com.example.workshop.service;

import com.example.workshop.models.dto.UserLoginDto;
import com.example.workshop.models.dto.UserRegisterDto;

public interface UserService {

    void loginUser(UserLoginDto userLoginDto);

    void registerUser(UserRegisterDto userRegisterDto);
}
