package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.manufacturer.Manufacturer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class InventoryItem {

    private String id;

    @NotNull(message = "Inventory name must be between 1 and 50 characters")
    @Size(min = 1, max = 50, message = "Inventory name must be between 1 and 50 characters")
    private String name;

    @NotNull
    //@Size(min = 24, max = 24, message = "Please enter a release date in format: yyyy-MM-ddThh:mm:ss.SSSZ")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-ddThh:mm:ss.SSSZ")
    private LocalDateTime releaseDate;

    @NotNull
    @Valid
    private Manufacturer manufacturer;

    public InventoryItem() {
    }

    public InventoryItem(String id, String name, LocalDateTime releaseDate, Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }

    public InventoryItem(String name, LocalDateTime releaseDate, Manufacturer manufacturer) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
