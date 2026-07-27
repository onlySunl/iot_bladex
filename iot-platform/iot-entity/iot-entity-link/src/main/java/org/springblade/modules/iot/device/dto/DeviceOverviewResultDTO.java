package org.springblade.modules.iot.device.dto;

import cn.hutool.core.map.MapUtil;
import com.mqttsnet.basic.interfaces.echo.EchoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
/**
 * @program: thinglinks-cloud
 * @description: 设备概况统计
 * @packagename: com.mqttsnet.thinglinks.device.vo.result
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-05-13 09:28
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(title = "DeviceOverviewResultDTO", description = "设备概况统计数据传输DTO")
public class DeviceOverviewResultDTO extends CustomBaseEntity implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "设备总量")
    private Integer totalDevicesCount;

    @Schema(description = "未连接量")
    private Integer notConnectedCount;

    @Schema(description = "在线量")
    private Integer onlineCount;

    @Schema(description = "离线量")
    private Integer offlineCount;

    @Schema(description = "未激活量")
    private Integer notActivatedCount;

    @Schema(description = "已激活量")
    private Integer activatedCount;

    @Schema(description = "已锁定量")
    private Integer lockedCount;

    @Schema(description = "网关设备量")
    private Integer gatewayDeviceCount;

    @Schema(description = "普通设备量")
    private Integer ordinaryCount;

    @Schema(description = "子设备量")
    private Integer subDeviceCount;}
