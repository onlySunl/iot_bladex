package org.springblade.basic.secure;


import org.springblade.basic.utils.aes.AesUtils;

/**
 * Description:
 * AES实现
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/8/2
 */
public class AESEncryptor implements Encryptor {
    @Override
    public String encrypt(String plainText) {
        return AesUtils.encryptWithDefaults(plainText);
    }

    @Override
    public String decrypt(String cipherText) {
        return AesUtils.decryptWithDefaults(cipherText);
    }
}