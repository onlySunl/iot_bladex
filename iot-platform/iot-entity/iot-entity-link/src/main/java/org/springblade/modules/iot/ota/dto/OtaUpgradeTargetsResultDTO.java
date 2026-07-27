package org.springblade.modules.iot.ota.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import org.springblade.common.base.Entity;
import org.springblade.common.echo.EchoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * <p>
 * 表单查询方法返回值DTO
 * OTA升级目标表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class OtaUpgradeTargetsResultDTO extends CustomBaseEntity implements Serializable, EchoVO {
    @Serial
    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();


    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 目标值(设备标识/分组ID/省市区域编码)
     */
    private String targetValue;
    /**
     * 目标状态(0:待推送,1:推送中,2:推送成功,3:推送失败)
     */
    private Integer targetStatus;
    /**
     * 描述
     */
    /**
     * 创建人组织
     */
    private Long createdOrgId;}
