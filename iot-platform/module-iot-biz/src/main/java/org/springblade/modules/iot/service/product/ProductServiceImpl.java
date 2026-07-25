package org.springblade.modules.iot.service.product;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.product.dto.ProductConfig;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.product.vo.ProductPageReqVO;
import org.springblade.modules.iot.controller.admin.product.vo.ProductSaveReqVO;
import org.springblade.modules.iot.controller.admin.product.vo.ProductUpdateReqVO;
import org.springblade.modules.iot.convert.ProductConvert;
import org.springblade.modules.iot.dal.mysql.deviceinfo.EiotDeviceInfoMapper;
import org.springblade.modules.iot.dal.mysql.product.ProductMapper;
import org.springblade.modules.iot.dal.redis.RedisKeyConstants;
import org.springblade.modules.iot.entity.ProductDO;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springblade.modules.iot.entity.ProductDO;

/**
 * 物联网产品 Service 实现
 *
 * @author EnjoyIot
 */
@Slf4j
@Service
@Validated
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, ProductDO> implements IProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private EiotDeviceInfoMapper deviceInfoMapper;

    @Resource
    private CacheManager cacheManager;

    @Override
    public Long createProduct(ProductSaveReqVO createReqVO) {
        String productKey = createReqVO.getProductKey();
        ProductDO exist = productMapper.getByProductKey(productKey);
        if (exist != null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.PRODUCT_KEY_REPEAT);
        }
        ProductDO product = BeanUtils.toBean(createReqVO, ProductDO.class);
        product.setProductSecret(UUID.randomUUID().toString(true));
        save(product);
        log.info("[产品] 创建成功: id={}, name={}, productKey={}", product.getId(), product.getName(), product.getProductKey());
        return product.getId();
    }

    @Override
    @CacheEvict(value = RedisKeyConstants.PRODUCT, key = "#updateReqVO.productKey")
    public void updateProduct(ProductUpdateReqVO updateReqVO) {
        validateProductExists(updateReqVO.getId());
        ProductDO updateObj = BeanUtils.toBean(updateReqVO, ProductDO.class);
        updateById(updateObj);
        log.info("[产品] 更新成功: id={}, name={}", updateReqVO.getId(), updateReqVO.getName());
    }

    @Override
    public Boolean deleteProduct(Long id) {
        ProductDO product = validateProductExists(id);
        if (deviceInfoMapper.selectCountByProductKey(product.getProductKey()) > 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.PRODUCT_DEVICE_EXISTS);
        }
        boolean ret = removeById(id);
        if (ret) {
            clearCache(RedisKeyConstants.PRODUCT, product.getProductKey());
            log.info("[产品] 删除成功: id={}, productKey={}", id, product.getProductKey());
        }
        return ret;
    }

    @Override
    public Product getProduct(Long id) {
        return ProductConvert.INSTANCE.convert(getById(id));
    }

    @Override
    public Product getByPk(String pk) {
        return ProductConvert.INSTANCE.convert(productMapper.getByProductKey(pk));
    }

    @Override
    public PageResult<Product> getProductPage(ProductPageReqVO pageReqVO) {
        IPage<ProductDO> iPage = productMapper.selectPage(new Page<ProductDO>(pageReqVO.getPageNo(), pageReqVO.getPageSize()), pageReqVO);
        return new PageResult<>(
                BeanUtils.toBean(iPage.getRecords(), Product.class),
                iPage.getTotal());
    }

    @Override
    @Cacheable(cacheNames = RedisKeyConstants.PRODUCT, key = "#pk", unless = "#result == null")
    public Product getProductByPkFromCache(String pk) {
        return getByPk(pk);
    }

    @Override
    public ProductConfig getConfigByPk(String pk) {
        // TODO: 实现产品配置获取
        return null;
    }

    @Override
    public boolean saveConfig(ProductConfig request) {
        // TODO: 实现产品配置保存
        return true;
    }

    private ProductDO validateProductExists(Long id) {
        ProductDO productDO = getById(id);
        if (productDO == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS);
        }
        return productDO;
    }

    private void clearCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }
}
