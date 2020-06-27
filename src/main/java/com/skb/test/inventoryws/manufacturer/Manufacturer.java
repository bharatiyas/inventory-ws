package com.skb.test.inventoryws.manufacturer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Manufacturer {

    @NotNull
    @Size(min = 1, max = 50, message = "Manufacturer name must be between 1 and 50 characters")
    private String name;
    private String homePage;
    private String phone;

    public Manufacturer() {
    }

    public Manufacturer(String name, String homePage, String phone) {
        this.name = name;
        this.homePage = homePage;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "name='" + name + '\'' +
                ", homePage='" + homePage + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
