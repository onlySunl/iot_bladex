package org.springblade.basic.secure;

import org.springblade.basic.utils.sm.Sm4Utils;

/**
 * Description:
 * SM4国密实现
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/8/2
 */
public class SM4Encryptor implements Encryptor {
    @Override
    public String encrypt(String plainText) {
        return Sm4Utils.encryptWithDefaults(plainText);
    }

    @Override
    public String decrypt(String cipherText) {
        return Sm4Utils.decryptWithDefaults(cipherText);
    }
}
