package com.skb.test.inventoryws.exception;

public class InventoryBadRequestException extends Exception {

    private String traceId;

    public InventoryBadRequestException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
