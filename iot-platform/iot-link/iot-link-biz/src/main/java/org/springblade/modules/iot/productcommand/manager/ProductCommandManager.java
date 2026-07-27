package org.springblade.modules.iot.productcommand.manager;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.productcommand.entity.ProductCommand;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 产品模型设备服务命令表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
public interface ProductCommandManager extends SuperManager<ProductCommand> {

    /**
     * 校验CODE
     *
     * @param serviceId 服务ID
     * @param commandCode 命令CODE
     * @return {@link List<ProductCommand>} 产品模型设备服务命令表
     */
    List<ProductCommand> checkCode(Long serviceId, String commandCode);

    /**
     * 根据服务ID查询命令信息
     *
     * @param serviceIds 服务ID集合
     * @return
     */
    List<ProductCommand> findAllByServiceIds(List<Long> serviceIds);
}


