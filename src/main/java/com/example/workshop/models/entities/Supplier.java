package com.example.workshop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "suppliers")
public class Supplier extends BaseEntity {

    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;

    public Supplier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
