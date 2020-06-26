package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.converter.EntityToModelConverter;
import com.skb.test.inventoryws.converter.InventoryConverter;
import com.skb.test.inventoryws.converter.ManufacturerConverter;
import com.skb.test.inventoryws.exception.InventoryResourceNotFoundException;
import com.skb.test.inventoryws.manufacturer.Manufacturer;
import com.skb.test.inventoryws.manufacturer.ManufacturerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;
    private ManufacturerRepository manufacturerRepository;
    private InventoryConverter inventoryConverter;

    public InventoryService(InventoryRepository inventoryRepository, ManufacturerRepository manufacturerRepository, InventoryConverter inventoryConverter) {
        this.inventoryRepository = inventoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.inventoryConverter = inventoryConverter;
    }

    public List<InventoryItem> getAllInventory(Integer skip, Integer limit) {
        Pageable paging = PageRequest.of(skip, limit);

        Page<InventoryEntity> pagedResult = inventoryRepository.findAll(paging);

        return pagedResult.getContent().stream()
                .map(pe -> inventoryConverter.convert(pe))
                .collect(Collectors.toList());
    }

    public InventoryItem getInventory(String inventoryId, String traceId) throws InventoryResourceNotFoundException {

        Optional<InventoryEntity> inventoryEntity = inventoryRepository.findByInventoryId(inventoryId);
        if(inventoryEntity.isPresent()) {

            return inventoryConverter.convert(inventoryEntity.get());
        } else {
            throw new InventoryResourceNotFoundException(traceId, "Inventory Id: " + inventoryId + " Not Found");
        }
    }
}
