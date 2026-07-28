package org.springblade.modules.iot.device.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;

/**
 * <p>
 * 实体类
 * 设备访问控制(ACL)规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-11 19:57:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_device_acl_rule", comment = "DeviceAclRule table")
public class DeviceAclRule extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 规则名称
     */
    @AutoColumn(value = "rule_name", comment = "规则名称")
    private String ruleName;

    /**
     * 产品标识
     */
    @AutoColumn(value = "product_identification", comment = "产品标识")
    private String productIdentification;

    /**
     * 设备标识
     */
    @AutoColumn(value = "device_identification", comment = "设备标识")
    private String deviceIdentification;

    /**
     * 规则级别(0:产品级、1:设备级)
     */
    @AutoColumn(value = "rule_level", comment = "规则级别(0:产品级、1:设备级)")
    private Integer ruleLevel;

    /**
     * 动作类型((0:全部、1:发布、2:订阅、3:取消订阅))
     */
    @AutoColumn(value = "action_type", comment = "动作类型((0:全部、1:发布、2:订阅、3:取消订阅))")
    private Integer actionType;
    /**
     * 规则优先级(0-1000,值越小优先级越高)
     */
    @AutoColumn(value = "priority", comment = "规则优先级(0-1000,值越小优先级越高)")
    private Integer priority;
    /**
     * MQTT主题模式(支持通配符)
     */
    @AutoColumn(value = "topic_pattern", comment = "MQTT主题模式(支持通配符)")
    private String topicPattern;
    /**
     * IP白名单地址(多个用逗号分隔)
     */
    @AutoColumn(value = "ip_whitelist", comment = "IP白名单地址(多个用逗号分隔)")
    private String ipWhitelist;
    /**
     * 决策(0:拒绝、1:允许)
     */
    @AutoColumn(value = "decision", comment = "决策(0:拒绝、1:允许)")
    private Boolean decision;
    /**
     * 是否启用
     */
    @AutoColumn(value = "enabled", comment = "是否启用")
    private Boolean enabled;
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;
}
