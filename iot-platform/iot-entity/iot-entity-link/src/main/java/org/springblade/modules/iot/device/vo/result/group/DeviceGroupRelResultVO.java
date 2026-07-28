package org.springblade.modules.iot.device.vo.result.group;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import org.springblade.basic.base.entity.Entity;
import org.springblade.basic.interfaces.echo.EchoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 表单查询方法返回值VO
 * 设备分组关系表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-23 14:06:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Schema(description = "设备分组关系")
public class DeviceGroupRelResultVO extends Entity implements Serializable, EchoVO {
    @Serial
    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();


    /**
     * 分组ID;#device_group
     */
    @Schema(description = "分组ID")
    private Long groupId;

    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    private String groupName;

    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;

    /**
     * 设备ID
     */
    @Schema(description = "设备ID(主键)")
    private Long deviceId;
    /**
     * 备注
     */
    /**
     * 最后修改人
     */
    @Schema(description = "最后修改人")
    private Long updatedBy;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

    /**
     * 设备名称
     */
    @Schema(description = "设备名称")
    private String deviceName;

    /**
     * 设备类型
     */
    @Schema(description = "设备类型")
    private Integer nodeType;

    /**
     * 设备状态
     */
    @Schema(description = "设备状态")
    private Integer deviceStatus;
    /**
     * 产品标识
     */
    @Schema(description = "产品标识")
    private String productIdentification;}
