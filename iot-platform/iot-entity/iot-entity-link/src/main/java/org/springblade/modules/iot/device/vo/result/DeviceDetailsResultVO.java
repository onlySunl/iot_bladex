package org.springblade.modules.iot.device.vo.result;

import org.springblade.model.vo.AuditableResultVO;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: iot-platform
 * @description: 设备详情结果VO
 * @packagename: org.springblade.modules.iot.device.vo.result
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-25 16:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "DeviceDetailsResultVO", description = "设备详情结果VO")
public class DeviceDetailsResultVO extends AuditableResultVO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端标识
     */
    @Schema(description = "客户端标识")
    private String clientId;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String userName;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    /**
     * 证书序列号
     */
    @Schema(description = "证书序列号")
    private String certSerialNumber;
    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 认证方式0-用户名密码，1-ssl证书
     */
    @Schema(description = "认证方式0-用户名密码，1-ssl证书")
    private Integer authMode;
    /**
     * 加密密钥
     */
    @Schema(description = "加密密钥")
    private String encryptKey;
    /**
     * 加密向量
     */
    @Schema(description = "加密向量")
    private String encryptVector;
    /**
     * 签名密钥
     */
    @Schema(description = "签名密钥")
    private String signKey;
    /**
     * 传输协议的加密方式
     */
    @Schema(description = "传输协议的加密方式")
    private Integer encryptMethod;
    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;
    /**
     * 设备名称
     */
    @Schema(description = "设备名称")
    private String deviceName;
    /**
     * 连接实例
     */
    @Schema(description = "连接实例")
    private String connector;
    /**
     * 设备描述
     */
    @Schema(description = "设备描述")
    private String description;
    /**
     * 设备状态
     */
    @Schema(description = "设备状态")
    private Integer deviceStatus;
    /**
     * 连接状态
     */
    @Schema(description = "连接状态")
    private Integer connectStatus;

    /**
     * 最新心跳时间
     */
    @Schema(description = "最新心跳时间")
    private LocalDateTime lastHeartbeatTime;

    /**
     * 设备标签
     */
    @Schema(description = "设备标签")
    private String deviceTags;
    /**
     * 产品标识
     */
    @Schema(description = "产品标识")
    private String productIdentification;
    /**
     * 绑定的产品版本序号(系统在设备注册 / 产品发布时写入,数据上报路径按此快照解析物模型,
     * 不随产品发布新版本变化 ── 灰度发布的关键路由依据)。
     */
    @Schema(description = "绑定的产品版本序号(系统在注册 / 发布灰度时写入,数据上报按此快照解析物模型,灰度路由依据)")
    private String boundProductVersionNo;
    /**
     * 软件版本
     */
    @Schema(description = "软件版本")
    private String swVersion;
    /**
     * 固件版本
     */
    @Schema(description = "固件版本")
    private String fwVersion;
    /**
     * sdk版本
     */
    @Schema(description = "sdk版本")
    private String deviceSdkVersion;
    /**
     * 子设备所属网关的 deviceIdentification（业务唯一标识，String；非主键 id）。
     * 仅 nodeType=SUBDEVICE 时有意义。前端按此值调 getDeviceDetailsByIdentification 拉网关详情。
     */
    @Schema(description = "网关设备的 deviceIdentification（业务唯一标识，String 类型；非主键 id）")
    private String gatewayId;
    /**
     * 设备类型
     */
    @Schema(description = "设备类型")
    private Integer nodeType;
    /**
     * 备注
     */

    @Schema(description = "产品详情结果VO")
    private ProductResultVO productResultVO;

    @Schema(description = "子设备详情结果VO")
    private List<DeviceResultVO> subDeviceResultVOList;

    @Schema(description = "设备位置信息VO")
    private DeviceLocationResultVO deviceLocationResultVO;
}
