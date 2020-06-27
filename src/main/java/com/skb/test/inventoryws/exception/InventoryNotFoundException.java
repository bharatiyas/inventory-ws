package com.skb.test.inventoryws.exception;

public class InventoryNotFoundException extends Exception {

    private String traceId;

    public InventoryNotFoundException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
