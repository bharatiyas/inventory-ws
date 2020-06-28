package com.skb.test.inventoryws.testutils;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestConstants {

    // Base URL
    public static final String API_BASE_URL = "http://localhost:";

    public static final String API_TRACE_ID = "Test-Trace-Id";

    // Test Manufacturer Details
    public static final String TEST_MANUFACTURER_ID = UUID.randomUUID().toString();
    public static final String TEST_MANUFACTURER_NAME = "TestPublisherName";
    public static final String TEST_MANUFACTURER_HOMEPAGE = "TestManufacturer@email.com";
    public static final String TEST_MANUFACTURER_PHONE = "112-233-455";

    // Test Inventory Details
    public static final String TEST_INVENTORY_ID = UUID.randomUUID().toString();
    public static final String TEST_INVENTORY_NAME = "TestInventoryName";
    public static final LocalDateTime TEST_RELEASEDATE = LocalDateTime.now();
    public static final String TEST_INVENTORY_PHONE = "112-233-455";
}
