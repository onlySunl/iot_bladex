package org.springblade.modules.iot.productproperty.manager;

import org.springblade.core.database.mybatis.BladeService;
import org.springblade.modules.iot.productproperty.entity.ProductProperty;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 产品模型服务属性表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
public interface ProductPropertyManager extends BladeService<ProductProperty> {

    /**
     * 根据服务ID查询属性信息
     *
     * @param serviceId
     * @return
     */
    List<ProductProperty> findAllByServiceId(Long serviceId);

    /**
     * 校验CODE
     *
     * @param serviceId    服务ID
     * @param propertyCode 属性CODE
     * @return {@link List<ProductProperty>} 产品模型服务属性表
     */
    List<ProductProperty> checkCode(Long serviceId, String propertyCode);

    /**
     * 根据服务ID查询属性信息
     *
     * @param serviceIds
     * @return
     */
    List<ProductProperty> findAllByServiceIds(List<Long> serviceIds);
}


