

package org.springblade.modules.iot.convert;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.entity.EiotDeviceInfoDO;
import org.springblade.modules.iot.entity.ProductDO;
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
public interface DeviceInfoConvert {
    DeviceInfoConvert INSTANCE = Mappers.getMapper(DeviceInfoConvert.class);

    DeviceInfo convert(EiotDeviceInfoDO ylDeviceInfoDO);

    PageResult<DeviceInfo> convertPage(PageResult<EiotDeviceInfoDO> selectPage);

    Product covertProduct(ProductDO productDO);

    List<DeviceInfo> convertList(List<EiotDeviceInfoDO> selectList);
}
