package org.springblade.modules.iot.device.manager.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.utils.StringUtils;
import org.springblade.modules.iot.device.entity.DeviceLocation;
import org.springblade.modules.iot.device.manager.DeviceLocationManager;
import org.springblade.modules.iot.device.mapper.DeviceLocationMapper;
import org.springblade.modules.iot.device.vo.query.DeviceLocationPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceLocationResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 通用业务实现类
 * 设备位置表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-05-30 23:05:31
 * @create [2023-05-30 23:05:31] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DeviceLocationManagerImpl extends SuperManagerImpl<DeviceLocationMapper, DeviceLocation> implements DeviceLocationManager {

    private final DeviceLocationMapper deviceLocationMapper;

    /**
     * 查询设备位置信息VO列表
     *
     * @param query 查询参数
     * @return {@link List < DeviceLocationResultVO >} 设备位置信息VO列表
     */
    @Override
    public List<DeviceLocationResultVO> getDeviceLocationResultVOList(DeviceLocationPageQuery query) {
        QueryWrapper<DeviceLocation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Objects.nonNull(query.getId()), DeviceLocation::getId, query.getId());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(query.getDeviceIdentification()), DeviceLocation::getDeviceIdentification, query.getDeviceIdentification());
        queryWrapper.lambda().in(CollUtil.isNotEmpty(query.getDeviceIdentificationList()), DeviceLocation::getDeviceIdentification, query.getDeviceIdentificationList());
        queryWrapper.lambda().eq(Objects.nonNull(query.getLatitude()), DeviceLocation::getLatitude, query.getLatitude());
        queryWrapper.lambda().eq(Objects.nonNull(query.getLongitude()), DeviceLocation::getLongitude, query.getLongitude());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(query.getFullName()), DeviceLocation::getFullName, query.getFullName());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(query.getProvinceCode()), DeviceLocation::getProvinceCode, query.getProvinceCode());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(query.getCityCode()), DeviceLocation::getCityCode, query.getCityCode());
        queryWrapper.lambda().in(CollUtil.isNotEmpty(query.getCityCodeList()), DeviceLocation::getCityCode, query.getCityCodeList());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(query.getRegionCode()), DeviceLocation::getRegionCode, query.getRegionCode());
        queryWrapper.lambda().orderByDesc(DeviceLocation::getCreatedTime);
        List<DeviceLocation> deviceLocations = deviceLocationMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(deviceLocations, DeviceLocationResultVO.class);
    }
}


