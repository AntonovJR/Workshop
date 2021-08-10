package com.example.workshop.models.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "cars")
public class Car extends BaseEntity {
    private String make;
    private String model;
    @Column(name = "vin_number")
    private String vinNumber;
    @Column(name = "engine_code")
    private String engineCode;
    @Column(name = "year_of_manufacture")
    private LocalDate manufactureYear;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    private Long odometer;
    private String color;
    @OneToMany(mappedBy = "car")
    private Set<RepairSheet> repairs;

    public Car() {
    }

    public Set<RepairSheet> getRepairs() {
        return repairs;
    }

    public void setRepairs(Set<RepairSheet> repairs) {
        this.repairs = repairs;
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

    public LocalDate getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(LocalDate manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
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
