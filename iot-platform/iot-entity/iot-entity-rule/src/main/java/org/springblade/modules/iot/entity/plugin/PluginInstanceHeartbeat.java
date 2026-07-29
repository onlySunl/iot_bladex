package org.springblade.modules.iot.entity.plugin;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;

import java.io.Serial;
import java.time.LocalDateTime;


/**
 * <p>
 * 实体类
 * 插件实例心跳表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:31:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_plugin_instance_heartbeat", comment = "PluginInstanceHeartbeat table")
public class PluginInstanceHeartbeat extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 实例唯一标识
     */
    
    @AutoColumn(value = "instance_identification", comment = "实例唯一标识")
    private String instanceIdentification;
    /**
     * 插件运行所在的机器 IP 地址
     */
    
    @AutoColumn(value = "machine_ip", comment = "插件运行所在的机器 IP 地址")
    private String machineIp;
    /**
     * 上次心跳时间
     */
    
    @AutoColumn(value = "last_heartbeat_time", comment = "上次心跳时间")
    private LocalDateTime lastHeartbeatTime;
    /**
     * 心跳间隔时间（秒）
     */
    
    @AutoColumn(value = "heartbeat_interval", comment = "心跳间隔时间（秒）")
    private Integer heartbeatInterval;
    /**
     * 心跳详细信息
     */
    
    @AutoColumn(value = "heartbeat_message", comment = "心跳详细信息")
    private String heartbeatMessage;
    /**
     * 扩展参数（预留）
     */
    
    @AutoColumn(value = "extend_params", comment = "扩展参数（预留）")
    private String extendParams;
    }
