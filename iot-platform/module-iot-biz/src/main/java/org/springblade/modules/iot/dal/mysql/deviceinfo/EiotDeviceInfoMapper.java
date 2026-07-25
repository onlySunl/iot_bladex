package org.springblade.modules.iot.dal.mysql.deviceinfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.api.device.dto.DeviceShortInfo;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceInfoPageReqVO;
import org.springblade.modules.iot.entity.EiotDeviceInfoDO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 设备信息 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotDeviceInfoMapper extends BladeMapper<EiotDeviceInfoDO> {

    IPage<DeviceShortInfo> selectPage(IPage<DeviceShortInfo> page, @Param("reqVO") DeviceInfoPageReqVO reqVO);

    Long selectCountByProductKey(@Param("productKey") String productKey);
}
