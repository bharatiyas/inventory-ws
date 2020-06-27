package com.skb.test.inventoryws.exception;

public class InventoryAlreadyExistException extends Exception {

    private String traceId;

    public InventoryAlreadyExistException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
