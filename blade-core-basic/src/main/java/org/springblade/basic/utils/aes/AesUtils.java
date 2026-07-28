package org.springblade.basic.utils.aes;

import java.nio.charset.StandardCharsets;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.secure.config.EncryptKeyManager;
import org.springframework.stereotype.Component;


/**
 * AES加密和解密工具类（支持IV向量）
 *
 * <p>此工具类提供了AES加密和解密功能，支持使用IV（初始化向量）以及Base64编码和解码操作。
 * 加密和解密使用AES算法，采用CBC模式和PKCS5Padding填充。</p>
 * <p>
 * 提供两种使用方式：
 * * 1. 默认模式：使用配置注入的密钥和IV
 * * 2. 自定义模式：动态传入密钥和IV
 *
 * @author mqttsnet
 */
@Slf4j
@Component
public class AesUtils {

    private static final Mode DEFAULT_MODE = Mode.CBC;
    private static final Padding DEFAULT_PADDING = Padding.PKCS5Padding;
    private static String KEY;
    private static String IV;

    public AesUtils(EncryptKeyManager keyManager) {
        EncryptKeyManager.KeyConfig config = keyManager.getConfig(EncryptKeyManager.Algorithm.AES);
        KEY = config.key();
        IV = config.iv();
    }

    /**
     * 检查密钥长度是否合法(AES要求16/24/32字节)
     *
     * @param key 密钥
     */
    private static void validateKey(String key) {
        int keyLength = key.getBytes().length;
        if (keyLength != 16 && keyLength != 24 && keyLength != 32) {
            throw new IllegalArgumentException("AES密钥长度必须为16/24/32字节");
        }
    }


    /**
     * AES解密（使用默认密钥和IV）
     *
     * @param data 待解密数据
     * @return 解密后的字符串
     */
    public static String decryptWithDefaults(String data) {
        return decrypt(data, KEY, IV);
    }

    /**
     * AES解密（使用自定义密钥和IV）
     *
     * @param data 待解密数据
     * @param key  自定义密钥
     * @param iv   自定义初始化向量
     * @return 解密后的字符串
     */
    public static String decryptWithCustom(String data, String key, String iv) {
        return decrypt(data, key, iv);
    }

    /**
     * AES加密（使用默认密钥和IV）
     *
     * @param param 待加密参数
     * @return 加密后的字符串
     */
    public static String encryptWithDefaults(String param) {
        return encrypt(param, KEY, IV);
    }

    /**
     * AES加密（使用自定义密钥和IV）
     *
     * @param param 待加密参数
     * @param key   自定义密钥
     * @param iv    自定义初始化向量
     * @return 加密后的字符串
     */
    public static String encryptWithCustom(String param, String key, String iv) {
        return encrypt(param, key, iv);
    }

    /**
     * AES解密方法
     *
     * @param data 待解密数据
     * @param key  密钥
     * @param iv   初始化向量
     * @return 解密后的字符串
     */
    private static String decrypt(String data, String key, String iv) {
        try {
            AES aes = new AES(DEFAULT_MODE, DEFAULT_PADDING,
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"),
                    new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));

            byte[] decryptBase64 = aes.decrypt(data);
            return new String(decryptBase64, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("AesUtils decrypt failure! data:{}", data, e);
            return "";
        }
    }

    /**
     * AES加密方法
     *
     * @param param 待加密参数
     * @param key   密钥
     * @param iv    初始化向量
     * @return 加密后的字符串
     */
    private static String encrypt(String param, String key, String iv) {
        try {
            AES aes = new AES(DEFAULT_MODE, DEFAULT_PADDING,
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"),
                    new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
            return aes.encryptHex(param);
        } catch (Exception e) {
            log.warn("AesUtils encrypt failure! param:{}", param, e);
            return "";
        }
    }

    public static void main(String[] args) {
        String param = "This is a test message to encrypt!";
        System.out.println("原始数据: " + param);

        // 自定义密钥和IV进行加密和解密（示例值，请替换为实际密钥）
        String customKey = "your-aes-key-hex";
        String customIv = "your-aes-iv-hex-";
        String encrypted = AesUtils.encryptWithCustom(param, customKey, customIv);
        System.out.println("自定义密钥加密后: " + encrypted);
        String decrypted = AesUtils.decryptWithCustom(encrypted, customKey, customIv);
        System.out.println("自定义密钥解密后: " + decrypted);
    }
}