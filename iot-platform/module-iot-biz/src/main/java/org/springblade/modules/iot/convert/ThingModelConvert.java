

package org.springblade.modules.iot.convert;


import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.entity.ThingModelDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 物模型 Convert
 *
 * @author EnjoyIot
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface ThingModelConvert {

    ThingModelConvert INSTANCE = Mappers.getMapper(ThingModelConvert.class);

    @Mapping(target = "model", source = "model", ignore = true)
    ThingModel convert(ThingModelDO thingModelDO);

}
