package com.skb.test.inventoryws.util;


public class InventoryWsUtils {


    public static boolean doesStringValueExist(String str) {

        if(str != null && str.trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
