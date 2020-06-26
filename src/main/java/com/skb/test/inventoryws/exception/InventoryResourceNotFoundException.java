package com.skb.test.inventoryws.exception;

public class InventoryResourceNotFoundException extends Exception {

    private String traceId;

    public InventoryResourceNotFoundException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
