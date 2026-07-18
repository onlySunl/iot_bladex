

package org.springblade.modules.iot.temporal.es.convert;



import org.springblade.modules.iot.temporal.es.document.DocTaskLog;
import org.springblade.modules.iot.temporal.es.document.DocVirtualDeviceLog;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDeviceLog;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description:
 */
@Mapper(builder = @Builder(disableBuilder = true))

public interface EsVirtualLogConvert {
    EsVirtualLogConvert INSTANCE = Mappers.getMapper(EsVirtualLogConvert.class);

    VirtualDeviceLog convert(DocVirtualDeviceLog o);

    DocVirtualDeviceLog convertDoc(VirtualDeviceLog log);
}

