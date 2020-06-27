package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.converter.InventoryConverter;
import com.skb.test.inventoryws.exception.InventoryAlreadyExistException;
import com.skb.test.inventoryws.exception.InventoryNotFoundException;
import com.skb.test.inventoryws.manufacturer.Manufacturer;
import com.skb.test.inventoryws.manufacturer.ManufacturerEntity;
import com.skb.test.inventoryws.manufacturer.ManufacturerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private static Logger logger = LoggerFactory.getLogger(InventoryService.class);

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
                .map(pe -> inventoryConverter.convertEntityToModel(pe))
                .collect(Collectors.toList());
    }

    public InventoryItem getInventory(String inventoryId, String traceId) throws InventoryNotFoundException {

        Optional<InventoryEntity> inventoryEntity = inventoryRepository.findByInventoryId(inventoryId);
        if(inventoryEntity.isPresent()) {

            return inventoryConverter.convertEntityToModel(inventoryEntity.get());
        } else {
            throw new InventoryNotFoundException(traceId, "Inventory Id: " + inventoryId + " Not Found");
        }
    }

    @Transactional
    public void addInventoryItem(InventoryItem inventoryItemToBeAdded, String traceId)
            throws InventoryAlreadyExistException {

        logger.debug("TraceId: {}, Request to add Book: {}", traceId, inventoryItemToBeAdded);
        InventoryEntity inventoryEntity = inventoryConverter.convertModelToEntity(inventoryItemToBeAdded);
        // Get the parent of the Book (Publisher is the parent). If not found throw an exception
        Manufacturer manufacturer = inventoryItemToBeAdded.getManufacturer();
        Optional<ManufacturerEntity> me = manufacturerRepository
                .findByName(manufacturer.getName());

        if(me.isPresent()) {
            if(!me.get().getPhone().equals(manufacturer.getPhone()) ||
                    !me.get().getHomePage().equals(manufacturer.getHomePage())) {
                throw new InventoryAlreadyExistException(traceId, "Manufacturer with same name but different phone/homePage found. Please verify manufacturer details.");
            }
            Optional<InventoryEntity> ie =
                    inventoryRepository.findByNameAndManufacturerEntity(inventoryItemToBeAdded.getName(), me.get());
            if(ie.isPresent()) {
                throw new InventoryAlreadyExistException(traceId, "Inventory with same name and same manufacturer aleardy exists");
            }
            inventoryEntity.setManufacturerEntity(me.get());
        }
        inventoryRepository.save(inventoryEntity);
        inventoryItemToBeAdded.setId(inventoryEntity.getInventoryId());
        logger.debug("TraceId: {}, Inventory Item added.", traceId);
    }
}
