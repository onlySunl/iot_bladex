package org.springblade.basic.utils;

import java.util.Base64;


/**
 * Base64 编码解码工具类
 * <p>
 * 提供 Base64 编码和解码的便捷方法，使用 Java 标准库中的 {@link Base64} 实现。
 * </p>
 *
 * @author mqttsnet
 * @since 1.0.0
 */
public final class Base64Utils {

    /**
     * 私有构造方法，防止工具类被实例化
     */
    private Base64Utils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * 将字节数组编码为 Base64 字符串
     *
     * @param data 要编码的字节数组
     * @return Base64 编码后的字符串
     * @throws IllegalArgumentException 如果输入数据为 null
     */
    public static String encode(byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * 将 Base64 字符串解码为字节数组
     *
     * @param base64 要解码的 Base64 字符串
     * @return 解码后的字节数组
     * @throws IllegalArgumentException 如果输入字符串为 null 或不是有效的 Base64 编码
     */
    public static byte[] decode(String base64) {
        if (base64 == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        try {
            return Base64.getDecoder().decode(base64);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Input string is not a valid Base64 encoded string", e);
        }
    }
}