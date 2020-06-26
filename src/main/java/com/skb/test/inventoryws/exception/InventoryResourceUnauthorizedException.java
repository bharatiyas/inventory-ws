package com.skb.test.inventoryws.exception;

public class InventoryResourceUnauthorizedException extends Exception {

    private String traceId;

    public InventoryResourceUnauthorizedException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
