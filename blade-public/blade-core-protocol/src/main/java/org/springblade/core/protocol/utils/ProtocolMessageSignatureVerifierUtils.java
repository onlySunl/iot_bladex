package org.springblade.core.protocol.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springblade.basic.utils.SnowflakeIdUtil;
import org.springblade.basic.utils.aes.AesUtils;
import org.springblade.basic.utils.sm.Sm4Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @program: thinglinks-util
 * @description: 协议消息处理Utils
 * @packagename: org.springblade.basic.utils
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-07 00:12
 **/
@Slf4j
public class ProtocolMessageSignatureVerifierUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 加密报文体
     *
     * @param dataBody      报文体
     * @param mid           消息ID
     * @param cipherFlag    加密标识 0：不加密 1：SM4加密 2：AES加密
     * @param signKey       数据签名密钥
     * @param encryptKey    加密密钥
     * @param encryptVector 加密向量
     * @return 加密后的报文体
     * @throws IOException
     */
    public static String encryptMessage(String dataBody, Long mid, Integer cipherFlag, String signKey, String encryptKey, String encryptVector) throws Exception {
        // 加密报文体
        String encryptedDataBody = encryptDataBody(dataBody, cipherFlag, encryptKey, encryptVector);

        // 计算数据签名
        long timeStamp = System.currentTimeMillis();
        String dataSign = calculateDataSign(timeStamp, signKey);

        // 构建加密后的报文
        Map<String, Object> head = new HashMap<>();
        head.put("mid", mid);
        head.put("cipherFlag", cipherFlag);
        head.put("timeStamp", timeStamp);

        Map<String, Object> encryptedMessage = new HashMap<>();
        encryptedMessage.put("head", head);
        encryptedMessage.put("dataBody", JSONUtil.isTypeJSON(encryptedDataBody) ? OBJECT_MAPPER.readValue(encryptedDataBody, Object.class) : encryptedDataBody);
        encryptedMessage.put("dataSign", dataSign);

        // 转换为 JSON 字符串
        return OBJECT_MAPPER.writeValueAsString(encryptedMessage);
    }


    /**
     * 解密报文体
     *
     * @param messageJson   加密后的报文
     * @param signKey       数据签名密钥
     * @param encryptKey    加密密钥
     * @param encryptVector 加密向量
     * @return 解密后的报文体
     * @throws IOException
     */
    public static String decryptMessage(String messageJson, String signKey, String encryptKey, String encryptVector) throws Exception {
        // 解析 JSON 报文
        Map<String, Object> message = OBJECT_MAPPER.readValue(messageJson, HashMap.class);

        // 获取报文头部信息
        Map<String, Object> head = (Map<String, Object>) message.get("head");
        int cipherFlag = (int) head.getOrDefault("cipherFlag", 0);
        long timeStamp = (long) head.getOrDefault("timeStamp", 0);

        // 获取加密后的报文体和数据签名
        Object dataBody = message.getOrDefault("dataBody", "");
        String encryptedDataBody = JSONUtil.isTypeJSON(dataBody.toString()) ? OBJECT_MAPPER.writeValueAsString(dataBody) : dataBody.toString();
        String dataSign = (String) message.getOrDefault("dataSign", "");

        // 验证数据签名
        if (CharSequenceUtil.isNotBlank(dataSign) && !verifyDataSign(timeStamp, signKey, dataSign)) {
            throw new IllegalArgumentException("Invalid data sign");
        }

        // 解密报文体
        return decryptDataBody(encryptedDataBody, cipherFlag, encryptKey, encryptVector);

    }

    private static String encryptDataBody(String dataBody, int cipherFlag, String encryptKey, String encryptVector) throws Exception {
        // 根据 cipherFlag 实现加密逻辑
        return switch (cipherFlag) {
            case 0 -> dataBody;
            case 1 ->
                // 使用 SM4 加密算法
                    sm4Encrypt(dataBody, encryptKey, encryptVector);
            case 2 ->
                // 使用 AES 加密算法
                    aesEncrypt(dataBody, encryptKey, encryptVector);
            default ->
                // 不加密
                    dataBody;
        };
    }


    private static String decryptDataBody(String encryptedDataBody, int cipherFlag, String encryptKey, String encryptVector) throws Exception {
        // 根据 cipherFlag 实现解密逻辑
        return switch (cipherFlag) {
            case 0 -> encryptedDataBody;
            case 1 ->
                // 使用 SM4 解密算法
                sm4Decrypt(encryptedDataBody, encryptKey, encryptVector);
            case 2 ->
                // 使用 AES 解密算法
                aesDecrypt(encryptedDataBody, encryptKey, encryptVector);
            default ->
                // 不解密
                encryptedDataBody;
        };
    }

    /**
     * 使用 SM4 加密算法加密数据
     *
     * @param data          原始数据
     * @param encryptKey    加密密钥
     * @param encryptVector 加密向量
     * @return 加密后的数据
     */
    private static String sm4Encrypt(String data, String encryptKey, String encryptVector) throws Exception {
        if (StrUtil.isBlank(data)) {
            return "";
        }
        return Sm4Utils.encryptWithCustom(data, encryptKey, encryptVector);
    }

    /**
     * 使用 SM4 解密算法解密数据
     *
     * @param encryptedData 加密后的数据
     * @param encryptKey    加密密钥
     * @param encryptVector 加密向量
     * @return 解密后的数据
     */
    private static String sm4Decrypt(String encryptedData, String encryptKey, String encryptVector) throws Exception {
        if (StrUtil.isBlank(encryptedData)) {
            return "";
        }
        return Sm4Utils.decryptWithCustom(encryptedData, encryptKey, encryptVector);
    }

    /**
     * 使用 AES 加密算法加密数据
     *
     * @param data          原始数据
     * @param encryptKey    加密密钥
     * @param encryptVector 加密向量
     * @return 加密后的数据
     */
    private static String aesEncrypt(String data, String encryptKey, String encryptVector) throws Exception {
        if (StrUtil.isBlank(data)) {
            return "";
        }
        return AesUtils.encryptWithCustom(data, encryptKey, encryptVector);
    }

    /**
     * 使用 AES 解密算法解密数据
     *
     * @param encryptedData 加密后的数据
     * @param encryptKey    加密密钥
     * @param encryptVector 加密向量
     * @return 解密后的数据
     */
    private static String aesDecrypt(String encryptedData, String encryptKey, String encryptVector) throws Exception {
        if (StrUtil.isBlank(encryptedData)) {
            return "";
        }
        return AesUtils.decryptWithCustom(encryptedData, encryptKey, encryptVector);
    }

    /**
     * 计算数据签名 (SHA256)
     *
     * @param timeStamp 时间戳
     * @param signKey   签名密钥
     * @return 数据签名
     */
    private static String calculateDataSign(long timeStamp, String signKey) {
        // 使用 timeStamp 和 signKey 计算签名
        return generateDataSign(timeStamp, signKey);
    }

    /**
     * 生成数据签名
     *
     * @param timeStamp 时间戳
     * @param signKey   签名密钥
     * @return 数据签名
     */
    public static String generateDataSign(long timeStamp, String signKey) {
        String dataToSign = timeStamp + ":" + signKey;
        return DigestUtils.sha256Hex(dataToSign);
    }

    /**
     * 验证协议内容是否合法（系统默认数据格式）
     *
     * @param jsonString
     * @return true:合法，false:不合法
     */
    public static boolean validateProtocolData(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);

            // 校验一级字段
            if (!validateJsonNode(rootNode, "head", JsonNode::isObject) || !validateJsonNode(rootNode, "dataSign", JsonNode::isTextual)) {
                return false;
            }

            JsonNode headNode = rootNode.get("head");

            // 校验二级字段和字段类型
            /*if (!validateJsonNode(headNode, "mid", JsonNode::isLong) ||
                    !validateJsonNode(headNode, "cipherFlag", JsonNode::isInt) ||
                    !validateJsonNode(headNode, "timeStamp", JsonNode::isLong)) {
                return false;
            }*/

            // 校验必传字段
            if (headNode.get("mid").asLong() <= 0 || headNode.get("timeStamp").asLong() <= 0) {
                return false;
            }

            // 校验加密标志范围
            int cipherFlag = headNode.get("cipherFlag").asInt();
            if (cipherFlag < 0 || cipherFlag > 2) {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean validateJsonNode(JsonNode node, String fieldName, Function<JsonNode, Boolean> validator) {
        return node.has(fieldName) && validator.apply(node.get(fieldName));
    }


    private static boolean verifyDataSign(long timeStamp, String signKey, String dataSign) {
        // 使用 timeStamp 和 signKey 计算签名
        String calculatedSign = calculateDataSign(timeStamp, signKey);
        // 比较计算出的签名和报文中的签名
        return calculatedSign.equals(dataSign);
    }

    public static void main(String[] args) {
        String dataBody = "1234";
        Long mId = Long.valueOf(SnowflakeIdUtil.nextId());
        Integer cipherFlag = 0;
        String signKey = "1234";
        String encryptKey = "yourncryptey";
        String encryptVector = "yourncryptvector";

        try {
            // 加密报文
            String encryptedMessage = encryptMessage(dataBody, mId, cipherFlag, signKey, encryptKey, encryptVector);
            System.out.println("Encrypted message: " + encryptedMessage);

            // 解密报文
            String decryptedDataBody = decryptMessage(encryptedMessage, signKey, encryptKey, encryptVector);
            System.out.println("Decrypted data body: " + decryptedDataBody);
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
