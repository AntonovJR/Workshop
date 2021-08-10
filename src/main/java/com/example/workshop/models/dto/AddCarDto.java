package com.example.workshop.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class AddCarDto {
    @Size(min = 2,message = "Enter valid make")
    private String make;
    @Size(min = 2, message = "Enter valid model")
    private String model;
    @Size(min = 5, message = "Enter valid VIN number")
    private String vinNumber;
    @Size(min = 3,message = "Enter valid engine code")
    private String engineCode;
    private String manufactureYear;
    private String fuelType;
    @Positive
    private Long odometer;
    private String color;

    public AddCarDto() {
    }

    public AddCarDto(String make, String model, String vinNumber, String engineCode, String manufactureYear,
                     String fuelType, Long odometer, String color) {
        this.make = make;
        this.model = model;
        this.vinNumber = vinNumber;
        this.engineCode = engineCode;
        this.manufactureYear = manufactureYear;
        this.fuelType = fuelType;
        this.odometer = odometer;
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getEngineCode() {
        return engineCode;
    }

    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getOdometer() {
        return odometer;
    }

    public void setOdometer(Long odometer) {
        this.odometer = odometer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
