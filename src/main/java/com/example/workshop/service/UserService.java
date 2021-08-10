package com.example.workshop.service;

import com.example.workshop.models.entities.User;

import java.io.IOException;

public interface UserService {

    void loginUser() throws IOException;

    void registerUser() throws IOException;

    User getLoggedUser();

    void logout();
}
