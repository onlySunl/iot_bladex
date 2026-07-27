package org.springblade.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64 工具类
 *
 * @author zuihou
 */
public class Base64Util {

    /**
     * 解析 Basic 认证头中的 client 信息
     * Basic clientId:clientSec → 解码 → [clientId, clientSec]
     *
     * @param basicHeader Basic clientId:clientSec
     * @return [clientId, clientSec]
     */
    public static String[] getClient(String basicHeader) {
        if (StrUtil.isEmpty(basicHeader)) {
            throw new IllegalArgumentException("客户端参数未传递");
        }
        return extractClient(basicHeader);
    }

    /**
     * 解码 clientId:clientSec
     */
    public static String[] extractClient(String client) {
        String token = base64Decoder(client);
        int index = token.indexOf(StrPool.COLON);
        if (index == -1) {
            throw new IllegalArgumentException("Basic认证格式无效");
        }
        return new String[]{token.substring(0, index), token.substring(index + 1)};
    }

    /**
     * Base64 解码
     *
     * @param val 参数
     * @return 解码后的值
     */
    @SneakyThrows
    public static String base64Decoder(String val) {
        byte[] decoded = Base64.getDecoder().decode(val.getBytes(StandardCharsets.UTF_8));
        return new String(decoded, StandardCharsets.UTF_8);
    }
}
