package org.springblade.modules.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.ProductFunction;

/**
 * IoT产品功能定义 Mapper 接口
 */
@Mapper
public interface ProductFunctionMapper extends BladeMapper<ProductFunction> {
}
