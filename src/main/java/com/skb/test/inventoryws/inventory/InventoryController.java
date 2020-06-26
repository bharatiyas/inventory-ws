package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.exception.InventoryResourceBadRequestException;
import com.skb.test.inventoryws.exception.InventoryResourceNotFoundException;
import com.skb.test.inventoryws.util.LibraryApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllInventory(@RequestParam(defaultValue = "0") Integer skip,
                                                               @RequestParam(defaultValue = "10") Integer limit,
                                                               @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId) {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        return new ResponseEntity<>(inventoryService.getAllInventory(skip, limit), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPublisher(@PathVariable String id,
                                          @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws InventoryResourceNotFoundException, InventoryResourceBadRequestException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        if(!LibraryApiUtils.doesStringValueExist(id)) {
            throw new InventoryResourceBadRequestException(traceId, "Please provide a valid inventoryId");
        }
        return new ResponseEntity<>(inventoryService.getInventory(id.trim(), traceId), HttpStatus.OK);
    }
}
