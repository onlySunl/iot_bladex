

package org.springblade.modules.iot.databridge.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源连接配置实体
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("iot_resource_connection")
public class ResourceConnection extends CustomBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 资源名称 */
    @TableField(value = "name")
    @AutoColumn(comment = "资源名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
    private String name;

    /** 资源类型 */
    @TableField(value = "type")
    @AutoColumn(comment = "资源类型", defaultValueType = DefaultValueEnum.NULL)
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    /** 插件类型 */
    @TableField(value = "plugin_type")
    @AutoColumn(comment = "插件类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
    private String pluginType;

    /** 主机地址 */
    @TableField(value = "host")
    @AutoColumn(comment = "主机地址", length = 255, defaultValueType = DefaultValueEnum.NULL)
    private String host;

    /** 端口号 */
    @TableField(value = "port")
    @AutoColumn(comment = "端口号", defaultValueType = DefaultValueEnum.NULL)
    private Integer port;

    /** 用户名 */
    @TableField(value = "username")
    @AutoColumn(comment = "用户名", length = 128, defaultValueType = DefaultValueEnum.NULL)
    private String username;

    /** 密码 */
    @TableField(value = "password")
    @AutoColumn(comment = "密码", length = 128, defaultValueType = DefaultValueEnum.NULL)
    private String password;

    /** 数据库名 */
    @TableField(value = "database_name")
    @AutoColumn(comment = "数据库名", length = 128, defaultValueType = DefaultValueEnum.NULL)
    private String databaseName;

    /** 扩展配置JSON */
    @TableField(value = "extra_config")
    @ColumnType("text")
    @AutoColumn(comment = "扩展配置JSON", defaultValueType = DefaultValueEnum.NULL)
    private String extraConfig;

    /** 状态：0禁用，1启用 */
    @TableField(value = "status")
    @AutoColumn(comment = "状态：0禁用，1启用", defaultValueType = DefaultValueEnum.NULL)
    private Integer status;

    /** 描述 */
    @TableField(value = "description")
    @ColumnType("text")
    @AutoColumn(comment = "描述", defaultValueType = DefaultValueEnum.NULL)
    private String description;

    /** 创建者 */
    @TableField(value = "create_by")
    @AutoColumn(comment = "创建者", length = 255, defaultValueType = DefaultValueEnum.NULL)
    private String createBy;

    /** 创建时间 */
    @TableField(value = "create_time")
    @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
    private LocalDateTime createTime;

    /** 更新者 */
    @TableField(value = "update_by")
    @AutoColumn(comment = "更新者", length = 255, defaultValueType = DefaultValueEnum.NULL)
    private String updateBy;

    /** 更新时间 */
    @TableField(value = "update_time")
    @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
    private LocalDateTime updateTime;

    /** 方向：IN-输入，OUT-输出，BOTH-双向 */
    @TableField(value = "direction")
    @AutoColumn(comment = "方向：IN-输入，OUT-输出，BOTH-双向", defaultValueType = DefaultValueEnum.NULL)
    @Enumerated(EnumType.STRING)
    private Direction direction;

    /** 数据流向：INPUT-输入，OUTPUT-输出，BIDIRECTIONAL-双向 */
    @TableField(value = "data_direction")
    @AutoColumn(comment = "数据流向：INPUT-输入，OUTPUT-输出，BIDIRECTIONAL-双向", defaultValueType = DefaultValueEnum.NULL)
    @Enumerated(EnumType.STRING)
    private DataDirection dataDirection;

    /** 动态配置JSON - 使用现有的extra_config字段存储 */
    private String dynamicConfig;

    /**
     * 资源类型枚举
     */
    public enum ResourceType {
        MYSQL, POSTGRESQL, H2, ORACLE, SQLSERVER, KAFKA, IOTDB, INFLUXDB, MQTT, HTTP, REDIS, ELASTICSEARCH, ALIYUN_IOT, TENCENT_IOT, HUAWEI_IOT
    }

    /**
     * 方向枚举
     */
    public enum Direction {
        IN,     // 输入
        OUT,    // 输出
        BOTH    // 双向
    }

    /**
     * 数据流向枚举
     */
    public enum DataDirection {
        INPUT,          // 数据输入
        OUTPUT,         // 数据输出
        BIDIRECTIONAL   // 双向流转
    }
}
