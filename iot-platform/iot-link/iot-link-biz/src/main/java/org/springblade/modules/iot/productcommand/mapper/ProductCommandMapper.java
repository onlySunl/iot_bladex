package org.springblade.modules.iot.productcommand.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.productcommand.entity.ProductCommand;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 产品模型设备服务命令表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet] 
 */
@Mapper
public interface ProductCommandMapper extends BladeMapper<ProductCommand> {

}

