/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.pojo.bridge.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
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

    /** 资源名称 */
    @TableField("name")
    private String name;

    /** 资源类型 */
    @TableField("type")
    @Enumerated(EnumType.STRING)
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

    /** 创建者 */

    /** 创建时间 */

    /** 更新者 */

    /** 更新时间 */

    /** 方向：IN-输入，OUT-输出，BOTH-双向 */
    @TableField("direction")
    @Enumerated(EnumType.STRING)
    private Direction direction;

    /** 数据流向：INPUT-输入，OUTPUT-输出，BIDIRECTIONAL-双向 */
    @TableField("data_direction")
    @Enumerated(EnumType.STRING)
    private DataDirection dataDirection;

    /** 动态配置JSON - 使用现有的extra_config字段存储 */

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
