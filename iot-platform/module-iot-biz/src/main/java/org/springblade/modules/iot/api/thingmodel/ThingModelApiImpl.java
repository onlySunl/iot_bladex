

package org.springblade.modules.iot.api.thingmodel;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.service.product.ThingModelService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class ThingModelApiImpl implements ThingModelApi {

    @Resource
    private ThingModelService thingModelService;

    @Override
    public ThingModel getThingModelByProductKeyFromCache(String pk) {
        return thingModelService.getThingModelByProductKeyFromCache(pk);
    }
}
