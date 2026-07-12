package org.springblade.modules.iot.protocol.http;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * HTTP 协议连接配置
 */
@Data
@Accessors(chain = true)
public class HttpConnectionConfig {

    /** 基础 URL */
    private String baseUrl;

    /** 请求超时时间（毫秒） */
    private int timeout = 5000;

    /** 连接超时时间（毫秒） */
    private int connectTimeout = 3000;

    /** 认证方式 (none/basic/bearer/apikey) */
    private String authType = "none";

    /** 认证凭据 */
    private String authCredential;

    /** 请求头 */
    private String headers;

    /** 内容类型 */
    private String contentType = "application/json";

    /** 是否启用 SSL */
    private boolean ssl;
}
