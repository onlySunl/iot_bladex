package org.springblade.modules.iot.dto.script;

import lombok.Data;

import java.util.Date;

/**
 * 商品信息
 *
 * @author mqttsnet
 */
@Data
public class ProductInfoDTO {

    private Integer id;

    private String name;

    private Double price;

    private Date createDate;
}
