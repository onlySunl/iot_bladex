package org.springblade.modules.iot.utils.x509;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * Description:
 * 证书序列号工具
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/7/18
 */
public class CertSerialNumberUtil {
    private CertSerialNumberUtil() {
    } // 禁止实例化

    /**
     * 获取标准化证书序列号（自动处理补零和格式）
     *
     * @param cert      证书对象（不可为null）
     * @param hexFormat true=十六进制, false=十进制
     * @param minLength 最小输出长度（≤0时不补零）
     * @return 标准化序列号字符串
     * @throws IllegalArgumentException 如果参数无效
     */
    public static String getSerialNumber(X509Certificate cert, boolean hexFormat, int minLength) {
        Objects.requireNonNull(cert, "Certificate must not be null");

        BigInteger serial = cert.getSerialNumber();
        String raw = hexFormat ? serial.toString(16).toUpperCase() : serial.toString();

        return minLength > 0 ?
                String.format("%0" + minLength + (hexFormat ? "X" : "d"), serial) :
                raw;
    }

    /**
     * 获取OpenSSL风格序列号（32字符十六进制，补零）
     */
    public static String getOpenSSLSerial(X509Certificate cert) {
        return getSerialNumber(cert, true, 32);
    }

    /**
     * 获取原始十进制序列号（不补零）
     */
    public static String getDecimalSerial(X509Certificate cert) {
        return getSerialNumber(cert, false, 0);
    }
}
