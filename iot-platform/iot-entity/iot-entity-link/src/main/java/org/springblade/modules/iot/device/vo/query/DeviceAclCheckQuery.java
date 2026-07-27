package org.springblade.modules.iot.device.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * Description:
 * 设备ACL权限校验请求参数
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceAclCheckQuery", description = "设备ACL权限校验Query")
public class DeviceAclCheckQuery implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID")
    private String tenantId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 客户端标识
     */
    @Schema(description = "客户端标识")
    @Size(max = 255, message = "客户端标识长度不能超过{max}")
    private String clientIdentifier;

    /**
     * 主题
     */
    @Schema(description = "主题")
    private String topic;

    /**
     * 动作类型
     */
    @Schema(description = "动作类型")
    private Integer actionType;

    /**
     * 协议类型 (ProtocolTypeEnum)
     */
    @Schema(description = "协议类型")
    private String protocolType;

    /**
     * 代理信息
     */
    @Schema(description = "代理信息")
    private String broker;

    /**
     * 通道ID
     */
    @Schema(description = "通道ID")
    private String channelId;

    /**
     * 客户端地址
     */
    @Schema(description = "客户端地址")
    private String remoteAddr;

    /**
     * 元数据
     */
    @Schema(description = "元数据")
    private Map<String, String> metadata;

}
