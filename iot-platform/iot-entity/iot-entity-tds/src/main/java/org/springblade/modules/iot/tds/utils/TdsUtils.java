package org.springblade.modules.iot.tds.utils;

/**
 * TDS Utils
 * Compatibility class for IoT migration
 */
public final class TdsUtils {
    private TdsUtils() {}
    
    public static String buildTableName(String productId) {
        return "device_" + productId.replaceAll("-", "_");
    }
}
