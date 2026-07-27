package org.springblade.modules.iot.sdk.response.ota;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 北向API-OTA获取可用升级版本响应
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotNorthboundOtaGetAvailableUpgradeVersionsResponse {

    /**
     * 设备唯一标识
     */
    private String deviceIdentification;

    /**
     * 产品唯一标识
     */
    private String productIdentification;

    /**
     * OTA包类型：0-软件包、1-固件包
     */
    private Integer packageType;

    /**
     * 当前版本号
     */
    private String currentVersion;

    /**
     * 可升级版本列表
     */
    private List<UpgradeVersionInfo> upgradeVersions;

    /**
     * 可升级版本信息
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpgradeVersionInfo {

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
}
