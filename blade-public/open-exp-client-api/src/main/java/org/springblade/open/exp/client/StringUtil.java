package org.springblade.open.exp.client;

/**
 * @author mqttsnet
 **/
public class StringUtil {
    public static boolean isEmpty(String value) {
        return null == value || value.isEmpty() || value.trim().isEmpty();
    }
}
