package org.springblade.basic.secure;

/**
 * Description:
 * 支持多算法的加密接口
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/8/2
 */
public interface Encryptor {

    String encrypt(String plainText);


    String decrypt(String cipherText);
}
