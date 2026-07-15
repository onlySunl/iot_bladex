

package org.springblade.modules.iot.pojo.bridge.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
import org.springblade.modules.iot.common.enums.DataDirection;
import org.springblade.modules.iot.common.enums.Direction;
import org.springblade.modules.iot.common.enums.ResourceType;

/**
 * 资源连接配置实体
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("iot_resource_connection")
public class ResourceConnection extends CustomBaseEntity {

    /** 资源名称 */
    @TableField("name")
    private String name;

    /** 资源类型 */
    @TableField("type")
    private ResourceType type;

    /** 插件类型 */
    @TableField("plugin_type")
    private String pluginType;

    /** 主机地址 */
    @TableField("host")
    private String host;

    /** 端口号 */
    @TableField("port")
    private Integer port;

    /** 用户名 */
    @TableField("username")
    private String username;

    /** 密码 */
    @TableField("password")
    private String password;

    /** 数据库名 */
    @TableField("database_name")
    private String databaseName;

    /** 扩展配置JSON */
    @TableField("extra_config")
    private String extraConfig;

    /** 状态：0禁用，1启用 */

    /** 描述 */
    @TableField("description")
    private String description;

    /** 方向：IN-输入，OUT-输出，BOTH-双向 */
    @TableField("direction")
    private Direction direction;

    /** 数据流向：INPUT-输入，OUTPUT-输出，BIDIRECTIONAL-双向 */
    @TableField("data_direction")
    private DataDirection dataDirection;

}
