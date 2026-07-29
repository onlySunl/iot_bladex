package org.springblade.basic.utils.sm;

import java.nio.charset.StandardCharsets;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.secure.config.EncryptKeyManager;
import org.springframework.stereotype.Component;

/**
 * 国密SM4对称加密工具类
 * <p>
 * 提供两种使用方式：
 * * 1. 默认模式：使用配置注入的密钥和IV
 * * 2. 自定义模式：动态传入密钥和IV
 *
 * @author mqttsnet
 */
@Slf4j
@Component
public class Sm4Utils {
    private static final Mode DEFAULT_MODE = Mode.CBC;
    private static final Padding DEFAULT_PADDING = Padding.PKCS5Padding;
    private static String KEY;
    private static String IV;

    public Sm4Utils(EncryptKeyManager keyManager) {
        EncryptKeyManager.KeyConfig config = keyManager.getConfig(EncryptKeyManager.Algorithm.AES);
        KEY = config.key();
        IV = config.iv();
    }

    /**
     * 使用配置密钥加密
     *
     * @param plaintext 明文数据
     * @return Hex格式密文（失败返回空字符串）
     */
    public static String encryptWithDefaults(String plaintext) {
        return encrypt(plaintext, KEY, IV);
    }

    /**
     * 使用配置密钥解密
     *
     * @param ciphertext Hex格式密文
     * @return 原始明文（失败返回空字符串）
     */
    public static String decryptWithDefaults(String ciphertext) {
        return decrypt(ciphertext, KEY, IV);
    }

    /**
     * 自定义密钥加密
     *
     * @param plaintext 明文数据
     * @param key       加密密钥（16/24/32字节）
     * @param iv        初始向量（16/24/32字节）
     */
    public static String encryptWithCustom(String plaintext, String key, String iv) {
        return encrypt(plaintext, key, iv);
    }

    /**
     * 自定义密钥解密
     *
     * @param ciphertext Hex格式密文
     * @param key        解密密钥（需与加密一致）
     * @param iv         初始向量（需与加密一致）
     */
    public static String decryptWithCustom(String ciphertext, String key, String iv) {
        return decrypt(ciphertext, key, iv);
    }

    /**
     * 加密核心方法
     */
    private static String encrypt(String plaintext, String key, String iv) {
        try {
            SM4 sm4 = new SM4(DEFAULT_MODE, DEFAULT_PADDING,
                    key.getBytes(StandardCharsets.UTF_8),
                    iv.getBytes(StandardCharsets.UTF_8));
            return sm4.encryptHex(plaintext);
        } catch (Exception e) {
            log.error("SM4加密失败 | 原文:{} | Key:{}", plaintext, key, e);
            return "";
        }
    }

    /**
     * 解密核心方法
     */
    private static String decrypt(String ciphertext, String key, String iv) {
        try {
            SM4 sm4 = new SM4(DEFAULT_MODE, DEFAULT_PADDING,
                    key.getBytes(StandardCharsets.UTF_8),
                    iv.getBytes(StandardCharsets.UTF_8));
            return sm4.decryptStr(ciphertext);
        } catch (Exception e) {
            log.error("SM4解密失败 | 密文:{} | Key:{}", ciphertext, key, e);
            return "";
        }
    }

    /**
     * 生成随机密钥（Hex格式）
     */
    public static String generateRandomKey() {
        return HexUtil.encodeHexStr(new SM4().getSecretKey().getEncoded());
    }

    /**
     * 生成随机初始向量（Hex格式）
     */
    public static String generateRandomIV() {
        return HexUtil.encodeHexStr(new SM4().getSecretKey().getEncoded());
    }

    public static void main(String[] args) {
        String key = "9c8a77d2c9c8d8b1";
        String iv = "b39e6f3d4c1b4f78";
        String data = "{\"deviceIdentification\":\"4626666658492417\",\"productIdentification\":\"4622683344760832\",\"msgType\":\"cloudReq\",\"serviceCode\":\"wendutiaojie\",\"cmd\":\"wendu\",\"params\":{\"wendu\":\"1\"}}";

        String encryptedData = Sm4Utils.encryptWithCustom(data, key, iv);
        System.out.println("加密后的数据: " + encryptedData);

        String decryptedData = Sm4Utils.decryptWithCustom(encryptedData, key, iv);
        System.out.println("解密后的数据: " + decryptedData);
    }

}
