package com.example.workshop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "repair_sheets")
public class RepairSheet extends BaseEntity {
    @OneToOne
    private Client client;
    @OneToOne
    private User mechanic;
    @OneToOne
    private Car car;
    @Column(name = "parts_price")
    private BigDecimal partsPrice;
    @Column(name = "labor_costs")
    private BigDecimal laborCosts;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "date_of_arrival")
    private LocalDate arrivalDate;
    @Column(name = "date_of_complete")
    private LocalDate completeDate;
    @Column(name = "is_ready")
    private Boolean isReady;

    public RepairSheet() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getMechanic() {
        return mechanic;
    }

    public void setMechanic(User mechanic) {
        this.mechanic = mechanic;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }
}
