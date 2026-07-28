package org.springblade.modules.iot.productproperty.service;

import org.springblade.basic.mvc.service.SuperService;
import org.springblade.modules.iot.productproperty.entity.ProductProperty;
import org.springblade.modules.iot.productproperty.vo.save.ProductPropertySaveVO;
import org.springblade.modules.iot.productproperty.vo.update.ProductPropertyUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 产品模型服务属性表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
public interface ProductPropertyService extends SuperService<Long, ProductProperty> {

    /**
     * 保存产品模型服务属性
     *
     * @param saveVO
     * @return
     */
    ProductProperty saveProductProperty(ProductPropertySaveVO saveVO);

    /**
     * 修改产品模型服务属性
     *
     * @param updateVO
     * @return
     */
    ProductProperty updateProductProperty(ProductPropertyUpdateVO updateVO);

    /**
     * 删除产品模型服务属性
     *
     * @param id
     * @return
     */
    Boolean deleteProductProperty(Long id);

    /**
     * 根据服务ID查询属性信息
     *
     * @param serviceId
     * @return
     */
    List<ProductProperty> findAllByServiceId(Long serviceId);

    /**
     * 根据服务ID查询属性信息
     *
     * @param serviceIds
     * @return
     */
    List<ProductProperty> findAllByServiceIds(List<Long> serviceIds);
}


