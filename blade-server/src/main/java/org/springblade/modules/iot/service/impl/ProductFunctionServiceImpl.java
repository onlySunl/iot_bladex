package org.springblade.modules.iot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.mapper.ProductFunctionMapper;
import org.springblade.modules.iot.pojo.entity.ProductFunction;
import org.springblade.modules.iot.service.IProductFunctionService;
import org.springframework.stereotype.Service;

/**
 * IoT产品功能定义 服务实现类
 */
@Service
public class ProductFunctionServiceImpl extends ServiceImpl<ProductFunctionMapper, ProductFunction> implements IProductFunctionService {
}
