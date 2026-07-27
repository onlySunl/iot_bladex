package org.springblade.modules.iot.sdk.request.product;

import lombok.Data;

/**
 * Description:
 * 北向API-查询产品物模型请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2026/02/02
 */
@Data
public class IotNorthboundProductGetThingModelRequest {

    /**
     * 产品标识（产品的唯一标识）
     * @mock PROD_001
     */
    private String productIdentification;

}
