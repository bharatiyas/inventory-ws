package com.skb.test.inventoryws.converter;

import com.skb.test.inventoryws.inventory.InventoryEntity;
import com.skb.test.inventoryws.inventory.InventoryItem;
import com.skb.test.inventoryws.util.InventoryWsUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InventoryConverter implements Converter<InventoryEntity, InventoryItem> {

    private ManufacturerConverter manufacturerConverter;

    public InventoryConverter(ManufacturerConverter manufacturerConverter) {
        this.manufacturerConverter = manufacturerConverter;
    }

    @Override
    public InventoryItem convertEntityToModel(InventoryEntity entity) {
        return new InventoryItem(entity.getInventoryId(), entity.getName(), entity.getReleaseDate(),
                manufacturerConverter.convertEntityToModel(entity.getManufacturerEntity()));
    }

    @Override
    public InventoryEntity convertModelToEntity(InventoryItem model) {

        if(!InventoryWsUtils.doesStringValueExist(model.getId()))
            return new InventoryEntity(UUID.randomUUID().toString(), model.getName(), model.getReleaseDate(),
                    manufacturerConverter.convertModelToEntity(model.getManufacturer()));

        else
            return new InventoryEntity(model.getId(), model.getName(), model.getReleaseDate(),
                    manufacturerConverter.convertModelToEntity(model.getManufacturer()));
    }
}
