package com.skb.test.inventoryws.converter;

import com.skb.test.inventoryws.inventory.InventoryEntity;
import com.skb.test.inventoryws.inventory.InventoryItem;
import org.springframework.stereotype.Service;

@Service
public class InventoryConverter implements EntityToModelConverter<InventoryEntity, InventoryItem> {

    private ManufacturerConverter manufacturerConverter;

    public InventoryConverter(ManufacturerConverter manufacturerConverter) {
        this.manufacturerConverter = manufacturerConverter;
    }

    @Override
    public InventoryItem convert(InventoryEntity entity) {
        return new InventoryItem(entity.getInventoryId(), entity.getName(), entity.getName(),
                manufacturerConverter.convert(entity.getManufacturerEntity()));
    }
}
