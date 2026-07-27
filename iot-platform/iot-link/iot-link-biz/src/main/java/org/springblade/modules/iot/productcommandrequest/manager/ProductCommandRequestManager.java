package org.springblade.modules.iot.productcommandrequest.manager;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.productcommandrequest.entity.ProductCommandRequest;
import org.springblade.modules.iot.productcommandrequest.vo.save.ProductCommandRequestSaveVO;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 产品模型服务命令属性请求参数
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
public interface ProductCommandRequestManager extends BaseService<ProductCommandRequest> {

    /**
     * 校验CODE
     *
     * @param serviceId 服务ID
     * @param commandId 命令ID
     * @param parameterCode 参数编码
     * @return {@link List<ProductCommandRequest>} 产品模型服务命令属性请求参数
     */
    List<ProductCommandRequest> checkCode(Long serviceId, Long commandId, String parameterCode);

    /**
     * 根据命令ID查询请求命令信息
     *
     * @param commandIds
     * @return
     */
    List<ProductCommandRequestSaveVO> selectCommandRequests(List<Long> commandIds);
}


