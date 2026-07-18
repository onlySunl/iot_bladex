

package org.springblade.modules.iot.api.device.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设备信息
 *
 * @author sjg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDevice implements Serializable {


    /**
     * 产品key
     */
    private String productKey;

    /**
     * 设备dn
     */
    private String deviceName;

    /**
     * 父设备id
     */
    private Long parentId;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 设备密钥
     */
    private String secret;

    private Long tenantId;
}
