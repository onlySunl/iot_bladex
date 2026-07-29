package org.springblade.basic.secure;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.utils.Base64Utils;
import org.springblade.basic.utils.aes.AesUtils;

/**
 * 加密解密工具类
 * 特性：
 * 1. 自动识别明文/密文（避免重复加密）
 * 2. AES + Base64处理流程
 * 3. 解密失败自动回退明文
 * 处理流程：
 * 加密：明文 -> AES加密 -> Base64编码 -> 添加ENC@前缀
 * 解密：移除ENC@前缀 -> Base64解码 -> AES解密 -> 返回明文
 *
 * @author mqttsnet
 */
@Slf4j
public class EncryptDecryptUtils {
    // 加密数据标识格式：ENC@ + Base64(AES密文)
    private static final String ENCRYPT_MARKER = "ENC@";
    private static final Pattern ENCRYPTED_PATTERN = Pattern.compile("^ENC@[A-Za-z0-9+/]+={0,2}$");

    /**
     * 加密数据
     *
     * @param plainText 明文
     * @return 加密后的密文（带ENC@前缀）
     */
    public static String encrypt(String plainText) {
        // 已经是加密数据则直接返回
        if (isEncrypted(plainText)) {
            log.debug("数据已加密，跳过重复加密");
            return plainText;
        }
        // 空字符串不加密
        if (plainText == null || plainText.isEmpty()) {
            return plainText;
        }

        try {
            // 1. AES加密
            String encrypted = AesUtils.encryptWithDefaults(plainText);
            // 2. Base64编码
            byte[] encryptedBytes = encrypted.getBytes(StandardCharsets.UTF_8);
            String base64Encoded = Base64Utils.encode(encryptedBytes);
            // 3. 添加标记
            return ENCRYPT_MARKER + base64Encoded;
        } catch (Exception e) {
            log.warn("加密失败，返回原始值。输入: {}", maskSensitiveData(plainText), e);
            return plainText;
        }
    }

    /**
     * 解密数据
     *
     * @param cipherText 密文（带ENC@前缀）
     * @return 解密后的明文
     */
    public static String decrypt(String cipherText) {
        if (cipherText == null || cipherText.isEmpty()) {
            return cipherText;
        }

        // 非加密数据直接返回
        if (!isEncrypted(cipherText)) {
            log.debug("数据未加密，直接返回");
            return cipherText;
        }

        try {
            // 1. 移除标记
            String base64Part = cipherText.substring(ENCRYPT_MARKER.length());
            // 2. Base64解码
            byte[] decodedBytes = Base64Utils.decode(base64Part);
            String encrypted = new String(decodedBytes, StandardCharsets.UTF_8);
            // 3. AES解密
            return AesUtils.decryptWithDefaults(encrypted);
        } catch (Exception e) {
            log.error("解密失败，返回原始值。密文: {}", cipherText, e);
            return cipherText;
        }
    }

    /**
     * 判断是否是加密数据
     */
    public static boolean isEncrypted(String data) {
        return data != null && ENCRYPTED_PATTERN.matcher(data).matches();
    }

    /**
     * 日志敏感数据脱敏显示
     */
    private static String maskSensitiveData(String data) {
        if (data == null) {
            return "null";
        }
        if (data.length() <= 4) {
            return "****";
        }
        return data.substring(0, 2) + "****" + data.substring(data.length() - 2);
    }
}
