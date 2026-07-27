package org.springblade.modules.iot.cache.vo.device;

import cn.hutool.core.map.MapUtil;
import com.mqttsnet.basic.interfaces.echo.EchoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 设备动作数据缓存VO
 * </p>
 *
 * @author mqttsnet
 * @date 2023-06-10 16:38:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "DeviceActionCacheVO", description = "设备动作数据缓存VO")
public class DeviceActionCacheVO extends CustomBaseEntity implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();


    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;
    /**
     * 动作类型
     */
    @Schema(description = "动作类型")
    private String actionType;
    /**
     * 内容信息
     */
    @Schema(description = "内容信息")
    private String message;
    /**
     * 状态
     */
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
