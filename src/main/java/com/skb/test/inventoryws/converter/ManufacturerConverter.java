package com.skb.test.inventoryws.converter;

import com.skb.test.inventoryws.manufacturer.Manufacturer;
import com.skb.test.inventoryws.manufacturer.ManufacturerEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManufacturerConverter implements Converter<ManufacturerEntity, Manufacturer> {

    @Override
    public Manufacturer convertEntityToModel(ManufacturerEntity entity) {
        return new Manufacturer(entity.getName(), entity.getHomePage(), entity.getPhone());
    }

    @Override
    public ManufacturerEntity convertModelToEntity(Manufacturer model) {
        return new ManufacturerEntity(UUID.randomUUID().toString(), model.getName(), model.getHomePage(), model.getPhone());
    }
}
