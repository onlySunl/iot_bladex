package org.springblade.modules.iot.productservice.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.productservice.entity.ProductServices;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 产品模型服务表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Mapper
public interface ProductServiceMapper extends BladeMapper<ProductServices> {

}

