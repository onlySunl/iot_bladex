package org.springblade.modules.nvr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.nvr.mapper.ProductMapper;
import org.springblade.modules.nvr.pojo.entity.Product;
import org.springblade.modules.nvr.service.IProductService;
import org.springframework.stereotype.Service;

/**
 * IoT产品 服务实现类
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
}
