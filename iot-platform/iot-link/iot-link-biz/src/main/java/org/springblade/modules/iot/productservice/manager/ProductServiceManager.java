package org.springblade.modules.iot.productservice.manager;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.productservice.entity.ProductServices;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 产品模型服务表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
public interface ProductServiceManager extends BaseService<ProductServices> {

    /**
     * 根据产品模型服务ID查询信息
     *
     * @param serviceId
     * @return
     */
    ProductServices findOneByProductServiceId(Long serviceId);

    /**
     * 查询产品模型服务列表
     *
     * @param find 产品模型服务
     * @return 产品模型服务集合
     */
    List<ProductServices> selectProductServicesList(ProductServices find);

    /**
     * 校验CODE
     *
     * @param productId 产品ID
     * @param serviceCode 服务CODE
     * @return {@link List<ProductServices>} 产品模型服务集合
     */
    List<ProductServices> checkCode(Long productId, String serviceCode);
}


