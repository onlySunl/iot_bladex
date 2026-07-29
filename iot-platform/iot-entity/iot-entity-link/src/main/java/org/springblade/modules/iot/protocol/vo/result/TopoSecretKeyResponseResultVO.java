package org.springblade.modules.iot.protocol.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * @program: iot-platform
 * @description: 设备密钥响应信息
 * @packagename: org.springblade.modules.iot.protocol.vo.result
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2024-09-03 11:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Schema(title = "TopoSecretKeyResponseResultVO", description = "设备密钥信息")
public class TopoSecretKeyResponseResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 加密密钥
     */
    @Schema(description = "加密密钥")
    private String encryptKey;
    /**
     * 加密向量
     */
    @Schema(description = "加密向量")
    private String encryptVector;
    /**
     * 签名密钥
     */
    @Schema(description = "签名密钥")
    private String signKey;
    /**
     * 传输协议的加密方式：0-明文传输、1-SM4、2-AES
     */
    @Schema(description = "传输协议的加密方式：0-明文传输、1-SM4、2-AES")
    private Integer encryptMethod;
    /**
     * 设备标识
     */
    @Schema(description = "设备标识")
    private String deviceIdentification;
}