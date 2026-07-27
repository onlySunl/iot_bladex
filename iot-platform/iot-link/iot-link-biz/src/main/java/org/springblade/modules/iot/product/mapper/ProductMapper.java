package org.springblade.modules.iot.product.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 产品模型
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Mapper
public interface ProductMapper extends BladeMapper<Product> {

}

