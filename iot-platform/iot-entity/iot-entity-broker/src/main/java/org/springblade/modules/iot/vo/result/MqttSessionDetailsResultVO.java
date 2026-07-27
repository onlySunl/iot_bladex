package org.springblade.modules.iot.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: MqttSessionDetailsResultVO
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * MQTT 会话详细信息的返回结果
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/11/5       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/11/5 15:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "MqttSessionDetailsResultVO", description = "MQTT会话详细信息返回对象")
public class MqttSessionDetailsResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "租户 ID")
    private String tenantId;

    @Schema(description = "会话类型，如 MQTT")
    private String type;

    @Schema(description = "会话的元数据")
    private Metadata metadata;

    /**
     * 元数据类，包含会话的详细信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Metadata implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(description = "版本号")
        private String ver;

        @Schema(description = "用户 ID")
        private String userId;

        @Schema(description = "客户端 ID")
        private String clientId;

        @Schema(description = "频道 ID")
        private String channelId;

        @Schema(description = "地址")
        private String address;

        @Schema(description = "代理服务器信息")
        private String broker;

        @Schema(description = "会话类型")
        private String sessionType;
    }
}
