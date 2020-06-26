package com.skb.test.inventoryws.exception;

public class InventoryResourceBadRequestException extends Exception {

    private String traceId;

    public InventoryResourceBadRequestException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
