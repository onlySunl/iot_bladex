package org.springblade.modules.iot.sdk.response.ota;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-OTA拉取软固件信息响应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundOtaPullResponse {

    /**
     * 设备唯一标识
     */
    private String deviceIdentification;

    /**
     * 产品唯一标识
     */
    private String productIdentification;

    /**
     * OTA任务ID
     */
    private Long otaTaskId;

    /**
     * OTA升级任务名称
     */
    private String otaTaskName;

    /**
     * OTA包名称
     */
    private String packageName;

    /**
     * OTA包类型：0-软件包、1-固件包
     */
    private Integer packageType;

    /**
     * 版本号
     */
    private String version;

    /**
     * 文件下载地址
     */
    private String fileLocation;

    /**
     * 包描述
     */
    private String description;

    /**
     * 自定义信息
     */
    private String customInfo;

    /**
     * 签名方法：0-MD5、1-SHA256
     */
    private Integer signMethod;

    /**
     * 签名值
     */
    private String sign;
}
