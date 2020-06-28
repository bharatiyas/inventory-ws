package com.skb.test.inventoryws.testutils;


import com.skb.test.inventoryws.inventory.InventoryEntity;
import com.skb.test.inventoryws.inventory.InventoryItem;
import com.skb.test.inventoryws.manufacturer.Manufacturer;
import com.skb.test.inventoryws.manufacturer.ManufacturerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InventoryWsTestUtil {

    private static int  inventoryCtr = 0;
    private static int manufacturerCtr = 0;

    /*public static Manufacturer createManufacturer() {
        return new Manufacturer(null, TestConstants.TEST_MANUFACTURER_NAME,
                TestConstants.TEST_MANUFACTURER_PHONE);
    }*/

    public static ManufacturerEntity createManufacturerEntity() {
        return new ManufacturerEntity(TestConstants.TEST_MANUFACTURER_ID + manufacturerCtr,
                TestConstants.TEST_MANUFACTURER_NAME + manufacturerCtr,
                TestConstants.TEST_MANUFACTURER_HOMEPAGE + manufacturerCtr,
                TestConstants.TEST_MANUFACTURER_PHONE + manufacturerCtr);
    }

    public static Manufacturer createManufacturer() {
        return new Manufacturer(TestConstants.TEST_MANUFACTURER_NAME + manufacturerCtr,
                TestConstants.TEST_MANUFACTURER_HOMEPAGE + manufacturerCtr,
                TestConstants.TEST_MANUFACTURER_PHONE + manufacturerCtr);
    }

    public static Optional<ManufacturerEntity> createManufacturerEntityOptional() {
        return Optional.of(createManufacturerEntity());
    }

    public static ManufacturerEntity createManufacturerEntityDifferentPhone() {
        return new ManufacturerEntity(TestConstants.TEST_MANUFACTURER_ID + manufacturerCtr,
                TestConstants.TEST_MANUFACTURER_NAME + manufacturerCtr,
                TestConstants.TEST_MANUFACTURER_HOMEPAGE + manufacturerCtr,
                "999-888-888");
    }

    public static Optional<ManufacturerEntity> createManufacturerEntityDifferentPhoneOptional() {
        return Optional.of(createManufacturerEntityDifferentPhone());
    }

    public static ManufacturerEntity createManufacturerEntityDifferentHomepage() {
        return new ManufacturerEntity(TestConstants.TEST_MANUFACTURER_ID + manufacturerCtr,
                TestConstants.TEST_MANUFACTURER_NAME + manufacturerCtr,
                "https://www.different.com",
                TestConstants.TEST_MANUFACTURER_PHONE + manufacturerCtr);
    }

    public static Optional<ManufacturerEntity> createManufacturerEntityDifferentHomepageOptional() {
        return Optional.of(createManufacturerEntityDifferentHomepage());
    }

    public static Optional<InventoryEntity> createInventoryEntityOptional() {
        return Optional.of(createInventoryEntity());
    }

    public static InventoryEntity createInventoryEntity() {
        return new InventoryEntity(TestConstants.TEST_INVENTORY_ID + inventoryCtr,
                TestConstants.TEST_INVENTORY_NAME + inventoryCtr, TestConstants.TEST_RELEASEDATE,
                createManufacturerEntity());
    }

    public static InventoryItem createInventoryItem() {
        return new InventoryItem(TestConstants.TEST_INVENTORY_NAME + inventoryCtr, TestConstants.TEST_RELEASEDATE,
                createManufacturer());
    }

    public static List<InventoryEntity> createInventoryEntities(int skip, int limit) {
        List<InventoryEntity> entities = IntStream.range(skip, limit).mapToObj(i -> {
            inventoryCtr++;
            manufacturerCtr++;
            return createInventoryEntity();
        }).collect(Collectors.toList());

        return entities;
    }
}
