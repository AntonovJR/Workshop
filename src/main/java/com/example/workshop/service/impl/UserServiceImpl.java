package com.example.workshop.service.impl;

import com.example.workshop.models.dto.UserLoginDto;
import com.example.workshop.models.dto.UserRegisterDto;
import com.example.workshop.models.entities.Position;
import com.example.workshop.models.entities.User;
import com.example.workshop.repository.UserRepository;
import com.example.workshop.service.UserService;
import com.example.workshop.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violation =
                validationUtil.violation(userLoginDto);
        if (!violation.isEmpty()) {
            System.out.println("----------------");
            violation.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            System.out.println("----------------");
        }
        User user = userRepository.findByUsername(userLoginDto.getUsername());
        if (user == null) {
            System.out.println("----------------");
            System.out.println("Username not found");
            System.out.println("----------------");
            return;

        }
        user = userRepository.findAllByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword())
                .orElse(null);
        if (user == null) {
            System.out.println("----------------");
            System.out.println("Wrong password");
            System.out.println("----------------");
            return;
        }


        loggedUser = user;
        System.out.println("----------------");
        System.out.printf("Successfully logged in %s%n", loggedUser.getUsername());
        System.out.println("----------------");

    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("----------------");
            System.out.println("Wrong confirm password");
            System.out.println("----------------");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violation =
                validationUtil.violation(userRegisterDto);
        if (!violation.isEmpty()) {
            System.out.println("----------------");
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            System.out.println("----------------");
            return;
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        if (checkUsername(user.getUsername()) && checkEmail(user.getEmail())) {
            userRepository.save(user);
            if (user.getId() == 1L) {
                user.setRole(Position.valueOf("ADMIN"));
            }else{
                user.setRole(Position.valueOf("CLIENT"));
            }
            userRepository.save(user);
            System.out.println("----------------");
            System.out.printf("%s was registered%n", user.getUsername());
            System.out.println("----------------");
        }


    }

    @Override
    public User getLoggedUser() {
        return this.loggedUser;
    }

    @Override
    public void logout() {
        this.loggedUser = null;
    }

    private boolean checkEmail(String email) {
        if (userRepository.findByEmail(email) != null) {
            System.out.println("----------------");
            System.out.println("Email is already in use");
            System.out.println("----------------");
            return false;
        }
        return true;
    }

    private boolean checkUsername(String username) {
        if (userRepository.findByUsername(username) != null) {
            System.out.println("----------------");
            System.out.println("Username is already in use");
            System.out.println("----------------");
            return false;

        }
        return true;
    }
}
