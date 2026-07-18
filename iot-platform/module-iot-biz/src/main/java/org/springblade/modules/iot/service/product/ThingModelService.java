

package org.springblade.modules.iot.service.product;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.controller.admin.thingmodel.vo.ThingModelSaveReqVO;

import jakarta.validation.Valid;

/**
 * 产品物模型 Service 接口
 *
 * @author EnjoyIot
 */
public interface ThingModelService {

    /**
     * 更新产品物模型
     *
     * @param updateReqVO 更新信息
     */
    void saveThingModel(@Valid ThingModelSaveReqVO updateReqVO);

    /**
     * 获得产品物模型
     *
     * @param id 编号
     * @return 产品物模型
     */
    ThingModel getThingModel(Long id);

    ThingModel getThingModelByProductKey(String productKey);

    ThingModel getThingModelByProductKeyFromCache(String productKey);

}
