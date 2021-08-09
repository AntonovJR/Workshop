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
            return;
        }
        User user = userRepository.findAllByUsernameAndPassword(userLoginDto.getUsername(),
                userLoginDto.getPassword())
                .orElse(null);
        if (user == null) {
            System.out.println("----------------");
            System.out.println("Incorrect username / password");
            System.out.println("----------------");
            return;
        }
        loggedUser = user;
        System.out.printf("Successfully logged in %s%n", loggedUser.getUsername());
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Wrong confirm password");
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
            if (user.getId() == 1L) {
                user.setRole(Position.valueOf("ADMIN"));
            }
            userRepository.save(user);
            System.out.printf("%s was registered%n", user.getUsername());
        }


    }

    private boolean checkEmail(String email) {
        if (userRepository.getByEmail(email) != null) {
            System.out.println("Email is already in use");
            return false;
        }
        return true;
    }

    private boolean checkUsername(String username) {
        if (userRepository.getByUsername(username) != null) {
            System.out.println("Username is already in use");
            return false;

        }
        return true;
    }
}
