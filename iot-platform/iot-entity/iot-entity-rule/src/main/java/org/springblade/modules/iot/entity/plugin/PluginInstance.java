package org.springblade.modules.iot.entity.plugin;
import org.springblade.common.entity.CustomBaseEntity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;


/**
 * <p>
 * 实体类
 * 插件实例信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:02:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_plugin_instance", comment = "PluginInstance table")
public class PluginInstance extends CustomBaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 实例唯一标识
     */
    @AutoColumn(value = "instance_identification", comment = "实例唯一标识")
    private String instanceIdentification;
    /**
     * 实例名称，用于标识实例的友好名称
     */
    @AutoColumn(value = "instance_name", comment = "实例名称，用于标识实例的友好名称")
    private String instanceName;
    /**
     * 应用名称，SpringBoot应用名称
     */
    @AutoColumn(value = "application_name", comment = "应用名称，SpringBoot应用名称")
    private String applicationName;
    /**
     * 实例运行所在的机器 IP 地址
     */
    @AutoColumn(value = "machine_ip", comment = "实例运行所在的机器 IP 地址")
    private String machineIp;
    /**
     * 实例可用端口范围起始值
     */
    @AutoColumn(value = "port_range_start", comment = "实例可用端口范围起始值")
    private Integer portRangeStart;
    /**
     * 实例可用端口范围结束值
     */
    @AutoColumn(value = "port_range_end", comment = "实例可用端口范围结束值")
    private Integer portRangeEnd;
    /**
     * 扩展参数（预留）
     */
    @AutoColumn(value = "extend_params", comment = "扩展参数（预留）")
    private String extendParams;
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;
    /**
     * 实例的权重
     */
    @AutoColumn(value = "weight", comment = "实例的权重")
    private Integer weight;
    /**
     * 实例的健康状态
     */
    @AutoColumn(value = "healthy", comment = "实例的健康状态")
    private Boolean healthy;
    /**
     * 实例是否启用
     */
    @AutoColumn(value = "enabled", comment = "实例是否启用")
    private Boolean enabled;
    /**
     * 实例是否为临时实例
     */
    @AutoColumn(value = "ephemeral", comment = "实例是否为临时实例")
    private Boolean ephemeral;
    /**
     * 实例所在集群名称
     */
    @AutoColumn(value = "cluster_name", comment = "实例所在集群名称")
    private String clusterName;
    /**
     * 实例心跳间隔时间(毫秒)
     */
    @AutoColumn(value = "heart_beat_interval", comment = "实例心跳间隔时间(毫秒)")
    private Long heartBeatInterval;
    /**
     * 实例心跳超时时间(毫秒)
     */
    @AutoColumn(value = "heart_beat_time_out", comment = "实例心跳超时时间(毫秒)")
    private Long heartBeatTimeOut;
    /**
     * 实例IP删除超时时间(毫秒)
     */
    @AutoColumn(value = "ip_delete_time_out", comment = "实例IP删除超时时间(毫秒)")
    private Long ipDeleteTimeOut;
    /**
     * 实例机器端口
     */
    @AutoColumn(value = "machine_port", comment = "实例机器端口")
    private String machinePort;

}
