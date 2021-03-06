package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.manufacturer.ManufacturerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntity, String> {

    List<InventoryEntity> findAll(Pageable pageable);
    Optional<InventoryEntity> findByInventoryId(String inventoryId);
    Optional<InventoryEntity> findByNameAndManufacturerEntity(String name, ManufacturerEntity manufacturerEntity);
}
