package org.springblade.modules.iot.entity.plugin;

import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.*;
import lombok.experimental.Accessors;
import org.springblade.basic.base.entity.Entity;

import java.io.Serial;


/**
 * <p>
 * 实体类
 * 插件与实例及端口管理表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:30:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
    
@AutoTable(value = "iot_plugin_instance_mapping", comment = "PluginInstanceMapping table")
public class PluginInstanceMapping extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 插件唯一标识插件唯一标识
     */
    
    @AutoColumn(value = "plugin_identification", comment = "插件唯一标识插件唯一标识")
    private String pluginIdentification;
    /**
     * 实例唯一标识
     */
    
    @AutoColumn(value = "instance_identification", comment = "实例唯一标识")
    private String instanceIdentification;
    /**
     * 插件在该实例上使用的端口号
     */
    
    @AutoColumn(value = "port", comment = "插件在该实例上使用的端口号")
    private Integer port;
    /**
     * 端口类型或用途（如 HTTP, HTTPS, 管理端口等）
     */
    
    @AutoColumn(value = "port_type", comment = "端口类型或用途（如 HTTP, HTTPS, 管理端口等）")
    private String portType;
    }
