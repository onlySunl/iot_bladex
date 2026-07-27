package org.springblade.modules.iot.device.vo.result.group;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import com.mqttsnet.basic.interfaces.echo.EchoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springblade.common.entity.TreeEntity;

/**
 * <p>
 * 表单查询方法返回值VO
 * 设备分组表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-19 18:05:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "DeviceGroupResultVO", description = "设备分组")
public class DeviceGroupResultVO extends TreeEntity<DeviceGroupResultVO, Long> implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    // id inherited from TenantEntity

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    private String groupName;
    /**
     * 分组类型
     */
    @Schema(description = "分组类型")
    private Integer type;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    private Boolean state;
    /**
     * 分组描述
     */
    @Schema(description = "分组描述")
    private String description;
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

    /** shadow Entity<Long>.createdBy,挂 @Echo 让 echoService 回填用户昵称到 echoMap.createdBy。 */
    @Schema(description = "创建人")
    private Long createdBy;

    /** shadow Entity<Long>.updatedBy,同上。 */
    @Schema(description = "最后修改人")
    private Long updatedBy;

    // parentId / sortValue inherited from TreeEntity}
}