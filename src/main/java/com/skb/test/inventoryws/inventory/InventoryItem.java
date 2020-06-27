package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.manufacturer.Manufacturer;

public class InventoryItem {

    private String id;
    private String name;
    private String releaseDate;
    private Manufacturer manufacturer;

    public InventoryItem() {
    }

    public InventoryItem(String id, String name, String releaseDate, Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
    }

    public InventoryItem(String name, String releaseDate, Manufacturer manufacturer) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
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
