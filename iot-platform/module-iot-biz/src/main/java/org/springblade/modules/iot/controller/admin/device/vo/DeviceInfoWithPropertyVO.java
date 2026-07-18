

package org.springblade.modules.iot.controller.admin.device.vo;


import org.springblade.modules.iot.controller.admin.product.vo.PropertyVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;


@Data
public class DeviceInfoWithPropertyVO {


    private Long deviceId;

    /**
     * 产品key
     */
    private String productKey;

    private String dn;

    @Schema(description="设备属性")
    private Map<String, PropertyVO> identifier2property;


    private boolean online;

    private Long onlineTime;

    private Long offlineTime;
}
