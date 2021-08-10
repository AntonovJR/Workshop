package com.example.workshop.service.impl;

import com.example.workshop.models.dto.AddCarDto;
import com.example.workshop.models.entities.Car;
import com.example.workshop.repository.CarRepository;
import com.example.workshop.service.CarService;
import com.example.workshop.service.UserService;
import com.example.workshop.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final BufferedReader reader;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil,
                          UserService userService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void addCar() throws IOException {
        System.out.print("Please enter make: ");
        String make = reader.readLine();
        System.out.print("Please enter model: ");
        String model = reader.readLine();
        System.out.print("Please enter vin number: ");
        String vinNumber = reader.readLine();
        System.out.print("Please enter engine code: ");
        String engineCode = reader.readLine();
        System.out.print("Please enter year of manufacture in format dd/MM/yyyy/: ");
        String manufactureYear = reader.readLine();
        System.out.print("Please enter fuel type(diesel, petrol or combination): ");
        String fuelType = reader.readLine().toUpperCase();
        System.out.print("Please enter odometer: ");
        Long odometer = Long.parseLong(reader.readLine());
        System.out.print("Please enter color: ");
        String color = reader.readLine();
        AddCarDto addCarDto = new AddCarDto(make, model, vinNumber, engineCode, manufactureYear, fuelType,
                odometer, color);
        Set<ConstraintViolation<AddCarDto>> violation = validationUtil.violation(addCarDto);
        if (!violation.isEmpty()) {
            System.out.println("----------------");
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            System.out.println("----------------");
            return;
        }
        Car car = modelMapper.map(addCarDto, Car.class);
        if (carRepository.findByVinNumber(car.getVinNumber()) == null) {
            car.setOwner(userService.getLoggedUser());
            carRepository.save(car);
            System.out.println("----------------");
            System.out.printf("Car with vin number %s was added%n", car.getVinNumber());
            System.out.println("----------------");
        } else {
            System.out.println("----------------");
            System.out.println("Car with vin number already exists in database");
            System.out.println("----------------");
        }


    }
}
