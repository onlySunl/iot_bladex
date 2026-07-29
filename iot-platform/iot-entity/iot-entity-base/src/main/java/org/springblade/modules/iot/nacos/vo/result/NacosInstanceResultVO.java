package org.springblade.modules.iot.nacos.vo.result;

import cn.hutool.core.util.NumberUtil;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * -----------------------------------------------------------------------------
 * File Name: NacosInstanceResultVO
 * -----------------------------------------------------------------------------
 * Description:
 * Naocs实例信息ResultVO
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/11/13       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/11/13 11:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(title = "NacosInstanceResultVO", description = "Nacos实例信息ResultVO")
public class NacosInstanceResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 获取实例心跳超时时间（从元数据中读取，若没有则返回默认值）。
     *
     * @return 实例心跳超时时间
     */
    @Schema(description = "获取实例心跳超时时间(milliseconds)")
    public long instanceHeartBeatTimeOut;
    /**
     * 获取 IP 删除超时值（从元数据中读取，若没有则返回默认值）。
     *
     * @return 实例的 IP 删除超时时间
     */
    @Schema(description = "获取实例的IP删除超时时间(milliseconds)")
    public long ipDeleteTimeout;
    /**
     * instanceId - 唯一标识实例的 ID.
     */
    @Schema(description = "实例唯一标识ID")
    private String instanceId;
    /**
     * ip - 实例的 IP 地址.
     */
    @Schema(description = "实例的IP地址")
    private String ip;
    /**
     * port - 实例的端口号.
     */
    @Schema(description = "实例的端口号")
    private int port;
    /**
     * weight - 实例的权重，默认值为1.0.
     */
    @Schema(description = "实例的权重")
    private double weight = 1.0D;
    /**
     * healthy - 实例的健康状态，默认为健康。
     */
    @Schema(description = "实例的健康状态")
    private Boolean healthy = true;
    /**
     * enabled - 实例是否启用，默认为启用。
     */
    @Schema(description = "实例是否启用")
    private Boolean enabled = true;
    /**
     * ephemeral - 实例是否为临时实例，默认为是临时实例。
     */
    @Schema(description = "实例是否为临时实例")
    private Boolean ephemeral = true;
    /**
     * clusterName - 实例所在的集群名称.
     */
    @Schema(description = "实例所在的集群名称")
    private String clusterName;
    /**
     * serviceName - 实例所属的服务名称.
     */
    @Schema(description = "实例所属的服务名称")
    private String serviceName;
    /**
     * metadata - 用户自定义的元数据，存储额外的键值对信息.
     */
    @Schema(description = "用户扩展的元数据")
    private Map<String, String> metadata = new HashMap<>();
    /**
     * 获取实例心跳间隔时间（从元数据中读取，若没有则返回默认值）。
     *
     * @return 实例心跳间隔时间
     */
    @Schema(description = "获取实例心跳间隔时间(milliseconds)")
    private long instanceHeartBeatInterval;

    /**
     * 获取实例的详细地址（IP:端口）。
     *
     * @return 返回实例的 IP 和端口的字符串表示
     */
    @Schema(description = "获取实例的地址，格式为 IP:端口")
    public String toInetAddr() {
        return ip + ":" + port;
    }

    /**
     * 判断实例元数据中是否包含指定的键。
     *
     * @param key 元数据键
     * @return 是否包含指定键
     */
    public boolean containsMetadata(final String key) {
        if (getMetadata() == null || getMetadata().isEmpty()) {
            return false;
        }
        return getMetadata().containsKey(key);
    }

    /**
     * 获取元数据中的值，若没有则返回默认值。
     *
     * @param key          元数据的键
     * @param defaultValue 默认值
     * @return 返回元数据中指定键的值，若不存在则返回默认值
     */
    private long getMetaDataByKeyWithDefault(final String key, final long defaultValue) {
        if (getMetadata() == null || getMetadata().isEmpty()) {
            return defaultValue;
        }
        final String value = getMetadata().get(key);
        if (NumberUtil.isNumber(value)) {
            return Long.parseLong(value);
        }
        return defaultValue;
    }

    /**
     * 获取元数据中的值，若没有则返回默认值。
     *
     * @param key          元数据的键
     * @param defaultValue 默认值
     * @return 返回元数据中指定键的值，若不存在则返回默认值
     */
    private String getMetaDataByKeyWithDefault(final String key, final String defaultValue) {
        if (getMetadata() == null || getMetadata().isEmpty()) {
            return defaultValue;
        }
        return getMetadata().get(key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceId, ip, port, weight, healthy, enabled, ephemeral, clusterName, serviceName, metadata);
    }


}
