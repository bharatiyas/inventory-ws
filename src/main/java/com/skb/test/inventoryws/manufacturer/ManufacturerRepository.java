package com.skb.test.inventoryws.manufacturer;

import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, String> {

    ManufacturerEntity findByManufacturerId(String manufacturerId);
}
