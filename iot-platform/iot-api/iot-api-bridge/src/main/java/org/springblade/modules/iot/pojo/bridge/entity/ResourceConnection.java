

package org.springblade.modules.iot.pojo.bridge.entity;
import org.springblade.modules.iot.common.enums.ResourceType;
import org.springblade.modules.iot.common.enums.Direction;
import org.springblade.modules.iot.common.enums.DataDirection;

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

    

    /** 资源名称 */
    @TableField(value = "name")
    @AutoColumn(comment = "资源名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
    private String name;

    /** 资源类型 */
    @TableField(value = "type")
    @AutoColumn(comment = "资源类型", defaultValueType = DefaultValueEnum.NULL)
  
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


    /** 描述 */
    @TableField(value = "description")
    @ColumnType("text")
    @AutoColumn(comment = "描述", defaultValueType = DefaultValueEnum.NULL)
    private String description;





    /** 方向：IN-输入，OUT-输出，BOTH-双向 */
    @TableField(value = "direction")
    @AutoColumn(comment = "方向：IN-输入，OUT-输出，BOTH-双向", defaultValueType = DefaultValueEnum.NULL)
  
    private Direction direction;

    /** 数据流向：INPUT-输入，OUTPUT-输出，BIDIRECTIONAL-双向 */
    @TableField(value = "data_direction")
    @AutoColumn(comment = "数据流向：INPUT-输入，OUTPUT-输出，BIDIRECTIONAL-双向", defaultValueType = DefaultValueEnum.NULL)
  
    private DataDirection dataDirection;

    /** 动态配置JSON - 使用现有的extra_config字段存储 */
    private String dynamicConfig;

}
