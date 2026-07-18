

package org.springblade.modules.iot.api.thingmodel;

import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;

public interface ThingModelApi {

    ThingModel getThingModelByProductKeyFromCache(String pk);

}
