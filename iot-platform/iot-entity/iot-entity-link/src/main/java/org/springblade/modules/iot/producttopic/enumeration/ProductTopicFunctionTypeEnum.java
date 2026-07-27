package org.springblade.modules.iot.producttopic.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 产品Topic功能类型枚举
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "ProductTopicFunctionTypeEnum", description = "产品Topic功能类型枚举")
public enum ProductTopicFunctionTypeEnum {
    DEFAULT(0, "默认Topic"),
    SECURITY_AUTH(1, "安全认证"),
    TOPOLOGY_MANAGEMENT(2, "设备拓扑管理"),
    THING_MODEL_COMM(3, "物模型通信"),
    TIME_SYNC(4, "时间同步"),
    OTA_UPDATE(5, "OTA升级"),
    AI_SERVICES(6, "AI服务");

    /**
     * 数值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;
}
