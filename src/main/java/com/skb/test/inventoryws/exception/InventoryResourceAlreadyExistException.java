package com.skb.test.inventoryws.exception;

public class InventoryResourceAlreadyExistException extends Exception {

    private String traceId;

    public InventoryResourceAlreadyExistException(String traceId, String message) {
        super(message);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
