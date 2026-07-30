package org.springblade.core.dinger.properties;

import lombok.Data;

/**
 * 飞书机器人配置
 */
@Data
public class FeiShuProperties extends NoticeProperties {

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用秘钥
     */
    private String appSecret;

}
