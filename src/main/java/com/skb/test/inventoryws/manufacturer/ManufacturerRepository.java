package com.skb.test.inventoryws.manufacturer;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, String> {

    Optional<ManufacturerEntity> findByManufacturerId(String manufacturerId);
    Optional<ManufacturerEntity> findByName(String name);

}
