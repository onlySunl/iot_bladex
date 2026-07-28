package org.springblade.basic.secure;

/**
 * Description:
 * 加密算法工厂
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/8/2
 */
public class EncryptorFactory {
    public static Encryptor getEncryptor(String algorithm) {
        return switch (algorithm.toUpperCase()) {
            case "AES" -> new AESEncryptor();
            case "SM4" -> new SM4Encryptor();
            default -> throw new IllegalArgumentException("不支持的加密算法: " + algorithm);
        };
    }
}
