package com.example.workshop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "parts")
public class Part extends BaseEntity {
    @Column(name = "part_number")
    private String partNumber;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal price;
    @ManyToMany
    private Set<Supplier> suppliers;
    @ManyToMany
    private Set<Car> compatibleCars;

    public Part() {
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> supplier) {
        this.suppliers = supplier;
    }

    public Set<Car> getCompatibleCars() {
        return compatibleCars;
    }

    public void setCompatibleCars(Set<Car> compatibleCars) {
        this.compatibleCars = compatibleCars;
    }
}
