

package org.springblade.modules.iot.service.product;


import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelRespVO;
import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelSaveReqVO;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 产品显示模型 Service 接口
 *
 * @author EnjoyIot
 */
public interface ShowModelService {

    /**
     * 创建产品显示模型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createShowModel(@Valid ShowModelSaveReqVO createReqVO);

    /**
     * 更新产品显示模型
     *
     * @param updateReqVO 更新信息
     */
    void saveShowModel(@Valid ShowModelSaveReqVO updateReqVO);

    /**
     * 删除产品显示模型
     *
     * @param id 编号
     */
    void deleteShowModel(Long id);

    /**
     * 获得产品显示模型
     *
     * @param id 编号
     * @return 产品显示模型
     */
    ShowModelRespVO getShowModel(Long id);


    List<ShowModelRespVO> getShowModelByProductKey(String productKey);
}
