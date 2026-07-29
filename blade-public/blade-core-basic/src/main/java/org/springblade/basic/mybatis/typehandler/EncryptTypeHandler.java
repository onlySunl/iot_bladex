package org.springblade.basic.mybatis.typehandler;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springblade.basic.utils.Base64Utils;
import org.springblade.basic.utils.aes.AesUtils;

/**
 * MyBatis字段加解密处理器（AES+Base64）
 * <p>
 * 特性：
 * 1. 自动识别明文/密文（避免重复加密）
 * 2. AES + Base64处理流程
 * 3. 解密失败自动回退明文
 * 处理流程：
 * 存储：明文 -> AES加密 -> Base64编码 -> 存入数据库
 * 读取：Base64解码 -> AES解密 -> 返回明文
 * </p>
 *
 * @author mqttsnet
 */
@Alias("encrypt")
@Slf4j
public class EncryptTypeHandler implements TypeHandler<String> {

    // 加密数据标识格式：ENC@ + Base64(AES密文)
    private static final String ENCRYPT_MARKER = "ENC@";
    private static final Pattern ENCRYPTED_PATTERN = Pattern.compile("^ENC@[A-Za-z0-9+/]+={0,2}$");

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {

        if (parameter == null) {
            setNullParameter(ps, i, jdbcType);
            return;
        }

        try {
            String valueToStore = processForStorage(parameter);
            ps.setString(i, valueToStore);
        } catch (Exception e) {
            log.error("字段加密失败，参数位置: {}, 原始值: {}", i, maskSensitiveData(parameter), e);
            throw new SQLException("字段加密失败", e);
        }
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String dbValue = rs.getString(columnName);
        return processFromDatabase(dbValue, columnName);
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String dbValue = rs.getString(columnIndex);
        return processFromDatabase(dbValue, "column_" + columnIndex);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String dbValue = cs.getString(columnIndex);
        return processFromDatabase(dbValue, "column_" + columnIndex);
    }

    /**
     * 处理待存储的数据
     *
     * @param input 待存储的数据
     * @return 加密后的数据
     */
    private String processForStorage(String input) {
        // 已经是加密数据则直接返回
        if (isEncryptedData(input)) {
            return input;
        }
        // 空字符串不加密
        if (input.isEmpty()) {
            return input;
        }

        try {
            // 1. AES加密
            String encrypted = AesUtils.encryptWithDefaults(input);
            // 2. Base64编码
            byte[] encryptedBytes = encrypted.getBytes(StandardCharsets.UTF_8);
            String base64Encoded = Base64Utils.encode(encryptedBytes);
            // 3. 添加标记
            return ENCRYPT_MARKER + base64Encoded;
        } catch (Exception e) {
            log.warn("加密失败，返回原始值。输入: {}", maskSensitiveData(input), e);
            return input;
        }
    }

    /**
     * 处理从数据库读取的数据
     *
     * @param dbValue   从数据库读取的值
     * @param fieldName 字段名
     * @return 解密后的数据
     */
    private String processFromDatabase(String dbValue, String fieldName) {
        if (dbValue == null || dbValue.isEmpty()) {
            return dbValue;
        }

        // 非加密数据直接返回
        if (!isEncryptedData(dbValue)) {
            return dbValue;
        }

        try {
            // 1. 移除标记
            String base64Part = dbValue.substring(ENCRYPT_MARKER.length());
            // 2. Base64解码
            byte[] decodedBytes = Base64Utils.decode(base64Part);
            String encrypted = new String(decodedBytes, StandardCharsets.UTF_8);
            // 3. AES解密
            return AesUtils.decryptWithDefaults(encrypted);
        } catch (Exception e) {
            log.error("字段解密失败，将返回原始值。字段: {}, 值: {}", fieldName, dbValue, e);
            return dbValue;
        }
    }

    /**
     * 判断是否是加密数据
     */
    private boolean isEncryptedData(String data) {
        return data != null && ENCRYPTED_PATTERN.matcher(data).matches();
    }

    /**
     * 日志敏感数据脱敏显示
     */
    private String maskSensitiveData(String data) {
        if (data == null) {
            return "null";
        }
        if (data.length() <= 4) {
            return "****";
        }
        return data.substring(0, 2) + "****" + data.substring(data.length() - 2);
    }

    private void setNullParameter(PreparedStatement ps, int i, JdbcType jdbcType)
            throws SQLException {
        int sqlType = (jdbcType != null) ? jdbcType.TYPE_CODE : JdbcType.NULL.TYPE_CODE;
        ps.setNull(i, sqlType);
    }

}
