package org.springblade.modules.iot.dto.script;

import lombok.Data;

/**
 * 订单信息DTO
 *
 * @author mqttsnet
 */
@Data
public class OrderInfoDTO {

    private Integer orderId;

    private String orderName;

    private String orderNumber;

    private String orderAmount;
}
