

package org.springblade.modules.iot.service.product;

import cn.hutool.core.lang.UUID;
import org.springblade.modules.iot.framework.common.exception.util.ServiceExceptionUtil;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.common.util.object.BeanUtils;
import org.springblade.modules.iot.framework.mybatis.core.query.MPJLambdaWrapperX;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.product.dto.ProductConfig;
import org.springblade.modules.iot.controller.admin.product.vo.ProductPageReqVO;
import org.springblade.modules.iot.controller.admin.product.vo.ProductSaveReqVO;
import org.springblade.modules.iot.controller.admin.product.vo.ProductUpdateReqVO;
import org.springblade.modules.iot.convert.ProductConvert;
import org.springblade.modules.iot.entity.CategoryDO;
import org.springblade.modules.iot.entity.ProductDO;
import org.springblade.modules.iot.dal.mysql.deviceinfo.EiotDeviceInfoMapper;
import org.springblade.modules.iot.dal.mysql.product.ProductMapper;
import org.springblade.modules.iot.dal.redis.RedisKeyConstants;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;


/**
 * 物联网产品 Service 实现类
 *
 * @author EnjoyIot
 */
@Service
@Validated
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private EiotDeviceInfoMapper deviceInfoMapper;

    @Resource
    private CacheManager cacheManager;

    @Override
    public Long createProduct(ProductSaveReqVO createReqVO) {
        // 插入
        ProductDO product = BeanUtils.toBean(createReqVO, ProductDO.class);
        String secret = UUID.randomUUID().toString(true);
        product.setProductSecret(secret);
        String productKey = createReqVO.getProductKey();

        if (productMapper.selectOne(ProductDO::getProductKey, productKey) != null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.PRODUCT_KEY_REPEAT);
        }

        productMapper.insert(product);
        // 返回
        return product.getId();
    }

    @Override
    @CacheEvict(value = RedisKeyConstants.PRODUCT, key = "#updateReqVO.productKey")
    public void updateProduct(ProductUpdateReqVO updateReqVO) {
        // 校验存在
        validateProductExists(updateReqVO.getId());
        // 更新
        ProductDO updateObj = BeanUtils.toBean(updateReqVO, ProductDO.class);
        productMapper.updateById(updateObj);
    }

    private void validateProductKeyExists(String productKey) {
        if (productMapper.selectOne(ProductDO::getProductKey, productKey) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS);
        }
    }

    @Override
    public Boolean deleteProduct(Long id) {
        ProductDO product = validateProductExists(id);
        // 检测是否有设备
        if(deviceInfoMapper.selectCountByProductKey(product.getProductKey())>0){
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.PRODUCT_DEVICE_EXISTS);
        }
        Boolean ret= productMapper.deleteById(id)>0;
        if(ret){
            String productKey = product.getProductKey();
            // 清除缓存
            clearCache(RedisKeyConstants.PRODUCT,productKey);

        }
        return ret;
    }

    // 清理缓存
    private void clearCache(String cacheName, String key){
        Cache cache = cacheManager.getCache(cacheName);
        if(cache!=null){
            cache.evict(key);
        }
    }

    private ProductDO validateProductExists(Long id) {
        ProductDO productDO = productMapper.selectById(id);
        if (productDO == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.PRODUCT_NOT_EXISTS);
        }
        return productDO;
    }

    @Override
    public Product getProduct(Long id) {
        return ProductConvert.INSTANCE.convert(productMapper.selectById(id));
    }

    @Override
    public Product getByPk(String pk) {
        return ProductConvert.INSTANCE.convert(productMapper.selectOne(ProductDO::getProductKey, pk));
    }

    @Override
    public PageResult<Product> getProductPage(ProductPageReqVO pageReqVO) {
        MPJLambdaWrapperX<ProductDO> wrapperX = new MPJLambdaWrapperX<ProductDO>()
                .selectAll(ProductDO.class)
                .selectAs(CategoryDO::getName, Product::getCategoryName)
                .eqIfPresent(ProductDO::getCategoryId, pageReqVO.getCategoryId())
                .likeIfPresent(ProductDO::getName, pageReqVO.getName())
                .eqIfPresent(ProductDO::getProductKey, pageReqVO.getProductKey())
                .eqIfPresent(ProductDO::getNodeType, pageReqVO.getNodeType())
                .eqIfPresent(ProductDO::getProtocolCode, pageReqVO.getProtocolCode());

        wrapperX
                .leftJoin(CategoryDO.class, CategoryDO::getId, ProductDO::getCategoryId);

        return productMapper.selectJoinPage(pageReqVO, Product.class, wrapperX);
    }

    @Override
    @Cacheable(cacheNames = RedisKeyConstants.PRODUCT, key = "#pk",
            unless = "#result == null")
    public Product getProductByPkFromCache(String pk) {
        return getByPk(pk);
    }

    @Override
    public ProductConfig getConfigByPk(String pk) {
        return null;
    }

    @Override
    public boolean saveConfig(ProductConfig request) {

        return true;
    }

}
