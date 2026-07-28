package org.springblade.modules.iot.device.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 设备档案信息表实体。
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_device", comment = "Device table")
public class Device extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 客户端标识
     */
    @AutoColumn(value = "client_id", comment = "客户端标识")
    private String clientId;
    /**
     * 用户名
     */
    @AutoColumn(value = "user_name", comment = "用户名")
    private String userName;
    /**
     * 密码
     */
    @AutoColumn(value = "password", comment = "密码")
    private String password;
    /**
     * 证书序列号
     */
    @AutoColumn(value = "cert_serial_number", comment = "证书序列号")
    private String certSerialNumber;
    /**
     * 应用ID
     */
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 认证方式0-用户名密码，1-ssl证书
     */
    @AutoColumn(value = "auth_mode", comment = "认证方式0-用户名密码，1-ssl证书")
    private Integer authMode;
    /**
     * 加密密钥
     */
    @AutoColumn(value = "encrypt_key", comment = "加密密钥")
    private String encryptKey;
    /**
     * 加密向量
     */
    @AutoColumn(value = "encrypt_vector", comment = "加密向量")
    private String encryptVector;
    /**
     * 签名密钥
     */
    @AutoColumn(value = "sign_key", comment = "签名密钥")
    private String signKey;
    /**
     * 传输协议的加密方式：0-明文传输、1-SM4、2-AES
     */
    @AutoColumn(value = "encrypt_method", comment = "传输协议的加密方式：0-明文传输、1-SM4、2-AES")
    private Integer encryptMethod;
    /**
     * 设备标识
     */
    @AutoColumn(value = "device_identification", comment = "设备标识")
    private String deviceIdentification;
    /**
     * 设备名称
     */
    @AutoColumn(value = "device_name", comment = "设备名称")
    private String deviceName;
    /**
     * 连接实例
     */
    @AutoColumn(value = "connector", comment = "连接实例")
    private String connector;
    /**
     * 设备描述
     */
    @AutoColumn(value = "description", comment = "设备描述")
    private String description;
    /**
     * 设备状态:1启用ENABLE || 2禁用DISABLE||未激活NOTACTIVE 0
     */
    @AutoColumn(value = "device_status", comment = "设备状态:1启用ENABLE || 2禁用DISABLE||未激活NOTACTIVE 0")
    private Integer deviceStatus;
    /**
     * 连接状态:在线：1ONLINE || 离线：2OFFLINE || 未连接：INIT 0
     */
    @AutoColumn(value = "connect_status", comment = "连接状态:在线：1ONLINE || 离线：2OFFLINE || 未连接：INIT 0")
    private Integer connectStatus;

    /**
     * 最近一次连接状态事件的因果时钟(HLC,64-bit),作 connect_status 的 event-time LWW CAS 对比基准:
     * 仅当 DB 内本字段严格小于新事件 hlc 才允许覆盖 connect_status,防止异步消费/乱序/抖动重连导致状态回退。
     * 非事件驱动的更新(运维强制下线 / xxl-job 探活补偿等)不更新本字段。
     */
    @AutoColumn(value = "last_status_event_hlc", comment = "最近一次连接状态事件的因果时钟(HLC,64-bit),作 connect_status 的 event-time LWW CAS 对比基准: 仅当 DB 内本字段严格小于新事件 hlc 才允许覆盖 connect_status,防止异步消费/乱序/抖动重连导致状态回退。 非事件驱动的更新(运维强制下线 / xxl-job 探活补偿等)不更新本字段。")
    private Long lastStatusEventHlc;

    /**
     * 最新心跳时间
     */
    @AutoColumn(value = "last_heartbeat_time", comment = "最新心跳时间")
    private LocalDateTime lastHeartbeatTime;

    /**
     * 设备标签
     */
    @AutoColumn(value = "device_tags", comment = "设备标签")
    private String deviceTags;
    /**
     * 产品标识
     */
    @AutoColumn(value = "product_identification", comment = "产品标识")
    private String productIdentification;
    /**
     * 软件版本
     */
    @AutoColumn(value = "sw_version", comment = "软件版本")
    private String swVersion;
    /**
     * 固件版本
     */
    @AutoColumn(value = "fw_version", comment = "固件版本")
    private String fwVersion;
    /**
     * sdk版本
     */
    @AutoColumn(value = "device_sdk_version", comment = "sdk版本")
    private String deviceSdkVersion;
    /**
     * 子设备所属网关的 deviceIdentification(业务唯一标识 String,不是网关实体的主键 id),仅 nodeType=SUBDEVICE 时有值。
     * 字段名叫 gatewayId 但语义是 gatewayDeviceIdentification:须用 getDeviceDetailsByIdentification(gatewayId) 换网关详情,
     * 不能用 getDeviceDetails(id) 这类按主键查询的接口。
     */
    @AutoColumn(value = "gateway_id", comment = "子设备所属网关的 deviceIdentification(业务唯一标识 String,不是网关实体的主键 id),仅 nodeType=SUBDEVICE 时有值。 字段名叫 gatewayId 但语义是 gatewayDeviceIdentification:须用 getDeviceDetailsByIdentification(gatewayId) 换网关详情, 不能用 getDeviceDetails(id) 这类按主键查询的接口。")
    private String gatewayId;
    /**
     * 设备类型:0普通设备 || 1网关设备 || 2子设备
     */
    @AutoColumn(value = "node_type", comment = "设备类型:0普通设备 || 1网关设备 || 2子设备")
    private Integer nodeType;
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;
    /**
     * 绑定的产品版本序号(对应 product_version.version_no),数据上报路径的物模型解析依据。
     */
    @AutoColumn(value = "bound_product_version_no", comment = "绑定的产品版本序号(对应 product_version.version_no),数据上报路径的物模型解析依据。")
    private String boundProductVersionNo;
    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;

}
