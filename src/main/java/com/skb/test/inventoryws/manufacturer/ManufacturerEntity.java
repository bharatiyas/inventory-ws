package com.skb.test.inventoryws.manufacturer;

import javax.persistence.*;

@Entity
@Table(name = "Manufacturer")
public class ManufacturerEntity {

    @Column(name = "manufacturer_id")
    @Id
    private String manufacturerId;

    @Column(name = "name")
    private String name;

    @Column(name = "home_page")
    private String homePage;

    @Column(name = "phone_number")
    private String phone;

    public ManufacturerEntity() {
    }

    public ManufacturerEntity(String manufacturerId, String name, String homePage, String phone) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.homePage = homePage;
        this.phone = phone;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
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
}
