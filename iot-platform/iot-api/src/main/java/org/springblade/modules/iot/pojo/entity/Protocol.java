package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.Index;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

/**
 * 协议定义实体
 *
 * @author blade-iot
 */
@Data
@TableName("iot_protocol")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "协议定义")
public class Protocol extends CustomBaseEntity {

    @Schema(description = "协议编码")
    @ColumnType("varchar(50)")
    @Index(unique = true)
    private String code;

    @Schema(description = "协议名称")
    @ColumnType("varchar(100)")
    private String name;

    @Schema(description = "协议类型: MQTT, HTTP, TCP, UDP, COAP, MODBUS, OPC_UA")
    @ColumnType("varchar(20)")
    private String type;

    @Schema(description = "协议描述")
    @ColumnType("text")
    private String description;

    @Schema(description = "编解码器类名")
    @ColumnType("varchar(255)")
    private String codecClass;

    @Schema(description = "协议配置(JSON)")
    @ColumnType("text")
    private String config;

    @Schema(description = "状态: 0-禁用 1-启用")
    private Integer status;
}
