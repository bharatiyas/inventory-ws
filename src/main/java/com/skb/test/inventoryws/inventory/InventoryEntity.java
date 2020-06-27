package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.manufacturer.ManufacturerEntity;

import javax.persistence.*;

@Entity
@Table(name = "Inventory")
public class InventoryEntity {

    @Column(name = "inventory_id")
    @Id
    private String inventoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private String releaseDate;

    public InventoryEntity() {
    }

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id",
            nullable = false)
    private ManufacturerEntity manufacturerEntity;

    public InventoryEntity(String inventoryId, String name, String releaseDate, ManufacturerEntity manufacturerEntity) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturerEntity = manufacturerEntity;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
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

    public ManufacturerEntity getManufacturerEntity() {
        return manufacturerEntity;
    }

    public void setManufacturerEntity(ManufacturerEntity manufacturerEntity) {
        this.manufacturerEntity = manufacturerEntity;
    }
}
