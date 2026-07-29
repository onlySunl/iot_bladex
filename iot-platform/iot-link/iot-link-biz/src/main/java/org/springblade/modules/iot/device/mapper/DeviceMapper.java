package org.springblade.modules.iot.device.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.springblade.core.mvc.mapper.SuperMapper;
import org.springblade.modules.iot.device.dto.DeviceOverviewResultDTO;
import org.springblade.modules.iot.device.dto.DeviceVersionDTO;
import org.springblade.modules.iot.device.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 设备档案信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Repository
public interface DeviceMapper extends SuperMapper<Device> {
    /**
     * 根据条件查询设备概述统计数据
     *
     * @param wrapper MyBatis-Plus标准Wrapper
     * @return {@link DeviceOverviewResultDTO} 统计结果
     */
    DeviceOverviewResultDTO selectDeviceOverview(@Param(Constants.WRAPPER) Wrapper<Device> wrapper);

    /**
     * 根据产品标识查询设备版本信息
     *
     * @param productIdentification 产品标识
     * @return {@link DeviceVersionDTO} 设备版本信息
     */
    DeviceVersionDTO selectDeviceVersionsByProduct(@Param("productIdentification") String productIdentification);

}