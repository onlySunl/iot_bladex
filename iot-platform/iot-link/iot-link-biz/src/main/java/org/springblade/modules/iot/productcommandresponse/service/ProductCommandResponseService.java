package org.springblade.modules.iot.productcommandresponse.service;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.productcommandresponse.entity.ProductCommandResponse;
import org.springblade.modules.iot.productcommandresponse.vo.result.ProductCommandResponseResultVO;
import org.springblade.modules.iot.productcommandresponse.vo.save.ProductCommandResponseSaveVO;
import org.springblade.modules.iot.productcommandresponse.vo.update.ProductCommandResponseUpdateVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 产品模型服务命令属性响应参数
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
public interface ProductCommandResponseService extends BaseService<ProductCommandResponse> {

    ProductCommandResponse saveProductCommandResponse(ProductCommandResponseSaveVO saveVO);

    /**
     * 修改产品模型设备响应服务命令属性
     *
     * @param updateVO
     * @return
     */
    ProductCommandResponse updateProductCommandResponse(ProductCommandResponseUpdateVO updateVO);

    /**
     * 删除产品模型设备响应服务命令属性
     *
     * @param id
     * @return
     */
    Boolean deleteProductCommandResponse(Long id);

    /**
     * 根据命令ID查询请求命令信息
     *
     * @param commandIds
     * @return
     */
    List<ProductCommandResponseResultVO> selectCommandResponses(List<Long> commandIds);
}

