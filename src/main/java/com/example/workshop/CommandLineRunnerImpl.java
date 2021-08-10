package com.example.workshop;

import com.example.workshop.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final BufferedReader reader;
    private final CarService carService;
    private final PartService partService;
    private final RepairSheetService repairSheetService;
    private final SupplierService supplierService;
    private final UserService userService;

    public CommandLineRunnerImpl(CarService carService, PartService partService,
                                 RepairSheetService repairSheetService,
                                 SupplierService supplierService, UserService userService) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.carService = carService;
        this.partService = partService;
        this.repairSheetService = repairSheetService;
        this.supplierService = supplierService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        do {
            loginOrRegister();
        } while (userService.getLoggedUser() == null);

        afterLogin();
    }

    private void afterLogin() throws Exception {
        if (userService.getLoggedUser().getRole().name().equals("MECHANIC")) {
            showMechanicOptions();
        } else if (userService.getLoggedUser().getRole().name().equals("ADMIN")) {
            showAdminOptions();
        } else if (userService.getLoggedUser().getRole().name().equals("CLIENT")) {
            showClientOptions();
        } else {
            System.out.println("Unexpected error please call Admin");
        }
    }

    private void showClientOptions() throws Exception {
        while (true) {
            System.out.println("###############");
            System.out.printf("Please choose option:" +
                    "%n1.Add Car" +
                    "%n2.Edit Car" +
                    "%n3.Delete Car" +
                    "%n4.Edit User Details" +
                    "%n5.Delete User" +
                    "%n12.Logout%n");
            System.out.println("###############");
            System.out.print("Option: ");

            int chosenOption = Integer.parseInt(reader.readLine());

            switch (chosenOption) {
                case 1 -> addCar();
                case 2 -> editCar();
                case 3 -> deleteCar();
                case 4 -> editUser();
                case 5 -> deleteUser();
                case 12 -> {
                    System.out.println("----------------");
                    System.out.println("Successfully logged out");
                    System.out.println("----------------");
                    userService.logout();
                    run();
                }
                default -> System.out.printf("----------------%nPlease enter valid option%n----------------%n");
            }
        }
    }

    private void showAdminOptions() throws Exception {
        while (true) {
            System.out.println("###############");
            System.out.printf("Please choose option:" +
                    "%n1.Add Car" +
                    "%n2.Edit Car" +
                    "%n3.Delete Car" +
                    "%n4.Edit User Details" +
                    "%n5.Delete User" +
                    "%n6.New Repair Sheet" +
                    "%n7.Edit Repair Sheet" +
                    "%n8.Add Part" +
                    "%n9.Edit Part" +
                    "%n10.Delete Part" +
                    "%n11.Report" +
                    "%n12.Logout%n");
            System.out.println("###############");
            System.out.print("Option: ");

            int chosenOption = Integer.parseInt(reader.readLine());
            switch (chosenOption) {
                case 1 -> addCar();
                case 2 -> editCar();
                case 3 -> deleteCar();
                case 4 -> editUser();
                case 5 -> deleteUser();
                case 6 -> newRepairSheet();
                case 7 -> editRepairSheet();
                case 8 -> addPart();
                case 9 -> editPart();
                case 10 -> deletePart();
                case 11 -> report();
                case 12 -> {
                    System.out.println("----------------");
                    System.out.println("Successfully logged out");
                    System.out.println("----------------");
                    userService.logout();
                    run();
                }
                default -> System.out.printf("----------------%nPlease enter valid option%n----------------%n");
            }
        }
    }

    private void report() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void deletePart() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void editPart() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void addPart() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void showMechanicOptions() throws Exception {
        while (true) {
            System.out.println("###############");
            System.out.printf("Please choose option:" +
                    "%n1.Add Car" +
                    "%n2.Edit Car" +
                    "%n3.Delete Car" +
                    "%n4.Edit User Details" +
                    "%n5.Delete User" +
                    "%n6.New Repair Sheet" +
                    "%n7.Edit Repair Sheet" +
                    "%n12.Logout%n");
            System.out.println("###############");
            System.out.print("Option: ");
            int chosenOption = Integer.parseInt(reader.readLine());
            switch (chosenOption) {
                case 1 -> addCar();
                case 2 -> editCar();
                case 3 -> deleteCar();
                case 4 -> editUser();
                case 5 -> deleteUser();
                case 6 -> newRepairSheet();
                case 7 -> editRepairSheet();
                case 12 -> {
                    System.out.println("----------------");
                    System.out.println("Successfully logged out");
                    System.out.println("----------------");
                    userService.logout();
                    run();
                }
                default -> System.out.printf("----------------%nPlease enter valid option%n----------------");
            }
        }
    }

    private void editRepairSheet() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void newRepairSheet() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void deleteUser() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void editUser() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void deleteCar() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void editCar() {
        //ToDo
        System.out.println("Coming soon");
    }

    private void addCar() throws IOException {
        carService.addCar();
    }

    private void loginOrRegister() throws IOException {
        System.out.println("###############");
        System.out.printf("Please choose option:%n1.Login%n2.Register%n");
        System.out.println("###############");
        System.out.print("Option: ");
        int chosenOption = Integer.parseInt(reader.readLine());
        switch (chosenOption) {
            case 1 -> userService.loginUser();
            case 2 ->  userService.registerUser();
            default -> System.out.printf("----------------%nPlease enter valid option%n----------------");
        }
    }
}
