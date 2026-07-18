

package org.springblade.modules.iot.temporal.es.convert;



import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.temporal.es.document.DocTaskLog;
import org.springblade.modules.iot.temporal.es.document.DocThingModelMessage;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description:
 */
@Mapper(builder = @Builder(disableBuilder = true))

public interface EsThingModelMessageConvert {
    EsThingModelMessageConvert INSTANCE = Mappers.getMapper(EsThingModelMessageConvert.class);

    @Mapping(source = "deviceName", target = "dn")
    ThingModelMessage convert(DocThingModelMessage content);

    @Mapping(source = "dn", target = "deviceName")
    DocThingModelMessage convertDoc(ThingModelMessage msg);
}


