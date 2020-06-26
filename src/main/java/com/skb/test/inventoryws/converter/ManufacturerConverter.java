package com.skb.test.inventoryws.converter;

import com.skb.test.inventoryws.manufacturer.Manufacturer;
import com.skb.test.inventoryws.manufacturer.ManufacturerEntity;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerConverter implements EntityToModelConverter<ManufacturerEntity, Manufacturer> {

    @Override
    public Manufacturer convert(ManufacturerEntity entity) {
        return new Manufacturer(entity.getName(), entity.getHomePage(), entity.getPhone());
    }
}
