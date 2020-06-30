package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.converter.InventoryConverter;
import com.skb.test.inventoryws.exception.InventoryAlreadyExistException;
import com.skb.test.inventoryws.exception.InventoryNotFoundException;
import com.skb.test.inventoryws.manufacturer.Manufacturer;
import com.skb.test.inventoryws.manufacturer.ManufacturerEntity;
import com.skb.test.inventoryws.manufacturer.ManufacturerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class acts as an abstraction layer between the Controller and Datasource.
 * It performs the necessary orchestration needed for the managing the Inventory Entity.
 */
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

    /**
     * Fetches a list available inventory entities in the system. Returns a page of inventory entities based on the
     * supplied skip and limit parameters.
     * @param skip - Number of records to skip for pagination
     * @param limit - Maximum number of records to return
     * @param traceId
     * @return
     */
    public List<InventoryItem> getAllInventory(Integer skip, Integer limit, String traceId) {
        Pageable pageable = PageRequest.of(skip, limit);

        //Page<InventoryEntity> pagedResult = inventoryRepository.findAll(pageable);
        List<InventoryEntity> pagedResult = inventoryRepository.findAll(pageable);

        logger.debug("TraceId: {}, Returning {} number inventory items", traceId, pagedResult.size());
        return pagedResult.stream()
                .map(pe -> inventoryConverter.convertEntityToModel(pe))
                .collect(Collectors.toList());
    }

    /**
     * Fetches an inventory item from the Datasource by InventoryId
     * @param inventoryId
     * @param traceId
     * @return
     * @throws InventoryNotFoundException
     */
    public InventoryItem getInventory(String inventoryId, String traceId) throws InventoryNotFoundException {

        Optional<InventoryEntity> inventoryEntity = inventoryRepository.findByInventoryId(inventoryId);
        if(inventoryEntity.isPresent()) {
            logger.debug("TraceId: {}, Returning Inventory with ID: {}", traceId, inventoryId);
            return inventoryConverter.convertEntityToModel(inventoryEntity.get());
        } else {
            throw new InventoryNotFoundException(traceId, "Inventory Id: " + inventoryId + " Not Found");
        }
    }

    /**
     * Adds an Inventory item to the Datasource. If a Manufacturer with matching details does not exist in the system,
     * it creates one. If a Manufacturer exists with matching details then uses the existing Manufacturer entity.
     * Otherwise, if a Manufacturer with same name exits, but other details do not match then it throws an error
     * alerting the consumer that the supplied details need to be rectified.
     * @param inventoryItemToBeAdded
     * @param traceId
     * @throws InventoryAlreadyExistException
     */
    public void addInventoryItem(InventoryItem inventoryItemToBeAdded, String traceId)
            throws InventoryAlreadyExistException {

        logger.debug("TraceId: {}, Request to add Inventory to DB: {}", traceId, inventoryItemToBeAdded);

        // Convent InventoryItem to Inventory Entity object
        InventoryEntity inventoryEntity = inventoryConverter.convertModelToEntity(inventoryItemToBeAdded);

        // Check if we already have a Manufacturer with the same name
        Manufacturer manufacturer = inventoryItemToBeAdded.getManufacturer();
        Optional<ManufacturerEntity> me = manufacturerRepository
                .findByName(manufacturer.getName());

        if(me.isPresent()) {
            // Found Manufacturer with the same name

            // Check if the details in the DB match with the ones supplied
            if(!me.get().getPhone().equals(manufacturer.getPhone()) ||
                    !me.get().getHomePage().equals(manufacturer.getHomePage())) {
                // Probably there is a an error in the Manufacturer details of the request
                throw new InventoryAlreadyExistException(traceId, "Manufacturer with same name but different phone/homePage found. Please verify manufacturer details.");
            }

            // Check if the inventory with the same name and from same Manufacturer is present in the DB already
            Optional<InventoryEntity> ie =
                    inventoryRepository.findByNameAndManufacturerEntity(inventoryItemToBeAdded.getName(), me.get());
            if(ie.isPresent()) {
                // Duplicate insertion being tried. Request is denied!
                throw new InventoryAlreadyExistException(traceId, "Inventory with same name and same manufacturer already exists");
            }

            // Inventory with the same name and from same Manufacturer is not found. All good to go.
            inventoryEntity.setManufacturerEntity(me.get());
        }

        // All checks done. Now persist the record in the DB
        inventoryRepository.save(inventoryEntity);

        // Set the Id in the InventoryItem object
        inventoryItemToBeAdded.setId(inventoryEntity.getInventoryId());
        logger.debug("TraceId: {}, Inventory added to DB.", traceId);
    }
}
