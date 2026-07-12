package org.springblade.modules.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.pojo.entity.Product;

/**
 * IoT产品 Mapper 接口
 */
@Mapper
public interface ProductMapper extends BladeMapper<Product> {
}
