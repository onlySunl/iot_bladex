

package org.springblade.modules.iot.convert;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDevice;
import org.springblade.modules.iot.controller.admin.virtualdevice.vo.EiotVirtualDeviceSaveReqVO;
import org.springblade.modules.iot.entity.VirtualDeviceDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */


@Mapper(builder = @Builder(disableBuilder = true))
public interface VirtualDeviceConvert {
    VirtualDeviceConvert INSTANCE = Mappers.getMapper(VirtualDeviceConvert.class);

    VirtualDevice convert(VirtualDeviceDO virtualDeviceDO);

    VirtualDeviceDO toDo(EiotVirtualDeviceSaveReqVO ruleInfo);


    VirtualDeviceDO toDo(VirtualDevice ruleInfo);

    PageResult<VirtualDevice> convertPage(PageResult<VirtualDeviceDO> selectPage);

    List<VirtualDevice> convertList(List<VirtualDeviceDO> selectList);

}
