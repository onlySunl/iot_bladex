package org.springblade.modules.iot.productcommand.service;

import org.springblade.basic.base.service.SuperService;
import org.springblade.modules.iot.productcommand.entity.ProductCommand;
import org.springblade.modules.iot.productcommand.vo.save.ProductCommandSaveVO;
import org.springblade.modules.iot.productcommand.vo.update.ProductCommandUpdateVO;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 产品模型设备服务命令表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
public interface ProductCommandService extends SuperService<Long, ProductCommand> {

    /**
     * 保存产品模型设备服务命令
     *
     * @param saveVO
     * @return
     */
    ProductCommand saveProductCommand(ProductCommandSaveVO saveVO);

    /**
     * 修改产品模型设备服务命令
     *
     * @param updateVO
     * @return
     */
    ProductCommand updateProductCommand(ProductCommandUpdateVO updateVO);

    /**
     * 删除产品模型设备服务命令
     *
     * @param id
     * @return
     */
    Boolean deleteProductCommand(Long id);

    /**
     * 根据服务ID查询命令信息
     * @param serviceIds
     * @return
     */
    List<ProductCommand> findAllByServiceIds(List<Long> serviceIds);
}


