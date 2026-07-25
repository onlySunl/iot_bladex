

package org.springblade.modules.iot.api.thingmodel;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.service.product.IThingModelService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class ThingModelApiImpl implements ThingModelApi {

    @Resource
    private IThingModelService thingModelService;

    @Override
    public ThingModel getThingModelByProductKeyFromCache(String pk) {
        return thingModelService.getThingModelByProductKeyFromCache(pk);
    }
}
