package org.springblade.modules.iot.device.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * @program: thinglinks-cloud
 * @description: 设备认证Query
 * @packagename: com.mqttsnet.thinglinks.device.vo.query
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-04-20 20:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceAuthenticationQuery", description = "设备认证Query")
public class DeviceAuthenticationQuery implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端标识
     */
    @Schema(description = "客户端标识")
    @Size(max = 255, message = "客户端标识长度不能超过{max}")
    private String clientIdentifier;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Size(max = 255, message = "用户名长度不能超过{max}")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @Size(max = 255, message = "密码长度不能超过{max}")
    private String password;

    /**
     * 协议类型 (ProtocolTypeEnum)
     */
    @Schema(description = "协议类型")
    @Size(max = 255, message = "协议类型长度不能超过{max}")
    private String protocolType;

    /**
     * 认证方式0-用户名密码，1-ssl证书
     */
    @Schema(description = "认证方式0-用户名密码，1-ssl证书")
    @NotNull(message = "请填写认证方式0-用户名密码，1-ssl证书")
    @Min(0)
    @Max(2)
    private Integer authMode;

    /**
     * 客户端证书（Base64编码）
     */
    @Schema(description = "客户端证书（Base64编码）")
    private String clientCertificate;

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

}
