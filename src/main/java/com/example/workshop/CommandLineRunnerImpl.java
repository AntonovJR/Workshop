package com.example.workshop;

import com.example.workshop.models.dto.UserLoginDto;
import com.example.workshop.models.dto.UserRegisterDto;
import com.example.workshop.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final BufferedReader reader;
    private final CarService carService;
    private final ClientService clientService;
    private final PartService partService;
    private final RepairSheetService repairSheetService;
    private final SupplierService supplierService;
    private final UserService userService;

    public CommandLineRunnerImpl(CarService carService, ClientService clientService,
                                 PartService partService, RepairSheetService repairSheetService,
                                 SupplierService supplierService, UserService userService) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.carService = carService;
        this.clientService = clientService;
        this.partService = partService;
        this.repairSheetService = repairSheetService;
        this.supplierService = supplierService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            loginOrRegister();
        }
    }

    private void loginOrRegister() throws IOException {
        System.out.println("###############");
        System.out.printf("Please choose option:%n1.Login%n2.Register%n");
        System.out.println("###############");
        System.out.print("Option:");
        int chosenOption = Integer.parseInt(reader.readLine());
        switch (chosenOption) {
            case 1 -> loginUser();
            case 2 -> registerUser();
            default -> System.out.println("Please enter valid option");
        }
    }

    private void registerUser() throws IOException {
        System.out.println("Please enter full name: ");
        String name = reader.readLine();
        System.out.println("Please enter phone number: ");
        String phoneNumber = reader.readLine();
        System.out.println("Please enter email: ");
        String email = reader.readLine();
        System.out.println("Please enter username between 3 and 30 symbols: ");
        String username = reader.readLine();
        System.out.println("Please enter password at least 6 symbols, must contain at least 1 uppercase, " +
                "1 lowercase letter and 1 digit: ");
        String password = reader.readLine();
        System.out.println("Please confirm password: ");
        String confirmPassword = reader.readLine();

        userService.registerUser(new UserRegisterDto(name,phoneNumber,email,username,password,confirmPassword));
    }

    private void loginUser() throws IOException {
        System.out.print("Please enter username:");
        String username = reader.readLine();
        System.out.print("Please enter password:");
        String password = reader.readLine();
        userService.loginUser(new UserLoginDto(username, password));

    }


}
