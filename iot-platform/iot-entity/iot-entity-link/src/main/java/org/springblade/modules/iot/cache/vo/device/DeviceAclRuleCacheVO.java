package org.springblade.modules.iot.cache.vo.device;

import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springblade.basic.base.entity.Entity;
import org.springblade.basic.interfaces.echo.EchoVO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 设备访问控制(ACL)规则 缓存VO
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
@Schema(title = "DeviceAclRuleCacheVO", description = "设备访问控制(ACL)规则")
public class DeviceAclRuleCacheVO extends Entity implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();


    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String ruleName;

    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;
    /**
     * 动作类型((0:全部、1:发布、2:订阅、3:取消订阅))
     */
    @Schema(description = "动作类型((0:全部、1:发布、2:订阅、3:取消订阅))")
    private Integer actionType;
    /**
     * 规则优先级(0-1000,值越小优先级越高)
     */
    @Schema(description = "规则优先级(0-1000,值越小优先级越高)")
    private Integer priority;
    /**
     * MQTT主题模式(支持通配符)
     */
    @Schema(description = "MQTT主题模式(支持通配符)")
    private String topicPattern;
    /**
     * IP白名单地址(多个用逗号分隔)
     */
    @Schema(description = "IP白名单地址(多个用逗号分隔)")
    private String ipWhitelist;
    /**
     * 决策(0:拒绝、1:允许)
     */
    @Schema(description = "决策(0:拒绝、1:允许)")
    private Boolean decision;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enabled;
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;
    /**
     * 产品标识
     */
    @Schema(description = "产品标识")
    private String productIdentification;
    /**
     * 规则级别(0:产品级、1:设备级)
     */
    @Schema(description = "规则级别(0:产品级、1:设备级)")
    private Integer ruleLevel;


 }
