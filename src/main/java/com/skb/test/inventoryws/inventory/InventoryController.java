package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.exception.InventoryAlreadyExistException;
import com.skb.test.inventoryws.exception.InventoryBadRequestException;
import com.skb.test.inventoryws.exception.InventoryNotFoundException;
import com.skb.test.inventoryws.util.InventoryWsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Controller to handle requests for Inventory API
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private static Logger logger = LoggerFactory.getLogger(InventoryController.class);

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Handle Get All Inventory API requests. List available inventory in the system.
     * Accepts skips and limit as Query Params
     * @param skip - Number of records to skip for pagination
     * @param limit - Maximum number of records to return
     * @param traceId
     * @return
     */
    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllfindByNameContaining(@RequestParam(defaultValue = "0") Integer skip,
                                                               @RequestParam(defaultValue = "10") Integer limit,
                                                               @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId) {

        logger.debug("TraceId: {}, Request to get Inventories: skip = {}, limit = {}", traceId, skip, limit);
        if(!InventoryWsUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
            logger.debug("TraceId: {}, Added ", traceId);
        }

        logger.debug("TraceId: {}, Returning response", traceId);
        return new ResponseEntity<>(inventoryService.getAllInventory(skip, limit, traceId), HttpStatus.OK);
    }

    /**
     * Handles the API request to return an inventory item by id.
     * @param id - Id of the Inventory item to be returned
     * @param traceId
     * @return
     * @throws InventoryNotFoundException
     * @throws InventoryBadRequestException
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<InventoryItem> getInventory(@PathVariable String id,
                                          @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws InventoryNotFoundException, InventoryBadRequestException {

        logger.debug("TraceId: {}, Request to get Inventory with Id: {}", traceId, id);
        if(!InventoryWsUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
            logger.debug("TraceId: {}, Added ", traceId);
        }

        if(!InventoryWsUtils.doesStringValueExist(id)) {
            throw new InventoryBadRequestException(traceId, "Please provide a valid inventoryId");
        }
        logger.debug("TraceId: {}, Returning response", traceId);
        return new ResponseEntity<>(inventoryService.getInventory(id.trim(), traceId), HttpStatus.OK);
    }

    /**
     * Handles the API request to add an item to the Inventory system. Accpets a JSON object of type InventoryItem
     * @param inventoryItem - Inventory Item details
     * @param traceId
     * @return
     * @throws InventoryAlreadyExistException
     */
    @PostMapping
    public ResponseEntity<InventoryItem> addInventory(@Valid @RequestBody InventoryItem inventoryItem,
                                          @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws InventoryAlreadyExistException {

        logger.debug("Request to add Inventory: {}", inventoryItem);
        if(!InventoryWsUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
            logger.debug("TraceId: {}, Added ", traceId);
        }

        inventoryService.addInventoryItem(inventoryItem, traceId);

        logger.debug("TraceId: {}, Returning response", traceId);
        return new ResponseEntity<>(inventoryItem, HttpStatus.CREATED);
    }
}
