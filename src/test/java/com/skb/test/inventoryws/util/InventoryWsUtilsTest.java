package com.skb.test.inventoryws.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryWsUtilsTest {

    @Test
    public void doesStringValueExist() {

        assertTrue(InventoryWsUtils.doesStringValueExist("ValueExist"));
        assertFalse(InventoryWsUtils.doesStringValueExist(""));
        assertFalse(InventoryWsUtils.doesStringValueExist(null));
    }
}