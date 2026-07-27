package org.springblade.modules.iot.productservice.service;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.productservice.entity.ProductServices;
import org.springblade.modules.iot.productservice.vo.save.ProductServiceSaveVO;
import org.springblade.modules.iot.productservice.vo.update.ProductServiceUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 产品模型服务表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
public interface ProductServiceService extends BaseService<Long, ProductServices> {

    /**
     * 保存产品模型服务
     *
     * @param saveVO
     * @return
     */
    ProductServices saveProductService(ProductServiceSaveVO saveVO);

    /**
     * 修改产品模型服务
     *
     * @param updateVO
     * @return
     */
    ProductServices updateProductService(ProductServiceUpdateVO updateVO);

    /**
     * 删除产品模型服务
     *
     * @param id
     * @return
     */
    Boolean deleteProductService(Long id);

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

}


