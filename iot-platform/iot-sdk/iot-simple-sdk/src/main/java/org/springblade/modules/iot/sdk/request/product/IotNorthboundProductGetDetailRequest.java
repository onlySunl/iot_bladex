package org.springblade.modules.iot.sdk.request.product;

import lombok.Data;

/**
 * Description:
 * 北向API-查询产品详情请求
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/6/22
 */
@Data
public class IotNorthboundProductGetDetailRequest {

    /**
     * 产品标识（产品的唯一标识）
     * @mock PROD_001
     */
    private String productIdentification;

}
