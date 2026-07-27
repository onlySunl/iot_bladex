package org.springblade.modules.iot.device.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.DeviceLocation;
import org.springblade.modules.iot.device.manager.DeviceLocationManager;
import org.springblade.modules.iot.device.service.DeviceLocationService;
import org.springblade.modules.iot.device.vo.query.DeviceLocationPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceLocationResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceLocationSaveVO;
import org.springblade.modules.iot.device.vo.update.DeviceLocationUpdateVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 设备位置表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-05-30 23:05:31
 * @create [2023-05-30 23:05:31] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceLocationServiceImpl extends BaseServiceImpl<DeviceLocationMapper, DeviceLocation> implements DeviceLocationService {

    /**
     * 保存设备位置信息
     *
     * @param deviceLocationSaveVO 设备位置信息
     * @return {@link DeviceLocationSaveVO} 保存完成的设备位置信息
     */
    @Override
    public DeviceLocationSaveVO saveDeviceLocation(DeviceLocationSaveVO deviceLocationSaveVO) {
        // 校验参数
        checkedDeviceLocationSaveVO(deviceLocationSaveVO);

        // 构建参数
        DeviceLocation deviceLocation = builderDeviceLocationSaveVO(deviceLocationSaveVO);

        // 保存设备位置信息
        baseMapper.save(deviceLocation);

        return BeanUtil.toBeanIgnoreError(deviceLocation, DeviceLocationSaveVO.class);
    }

    /**
     * 更新设备位置信息
     *
     * @param deviceLocationUpdateVO 设备位置信息
     * @return {@link DeviceLocationUpdateVO} 更新完成的设备位置信息
     */
    @Override
    public DeviceLocationUpdateVO updateDeviceLocation(DeviceLocationUpdateVO deviceLocationUpdateVO) {
        log.info("updateDeviceLocation updateVO:{}", deviceLocationUpdateVO);

        checkedDeviceLocationUpdateVO(deviceLocationUpdateVO);

        deviceLocationUpdateVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());

        //构建参数
        Builder<DeviceLocation> deviceLocationBuilder = builderDeviceLocationUpdateVO(deviceLocationUpdateVO);

        //更新
        baseMapper.updateById(deviceLocationBuilder.with(DeviceLocation::setId, deviceLocationUpdateVO.getId()).build());
        return deviceLocationUpdateVO;
    }

    /**
     * 查询设备位置信息VO列表
     *
     * @param query 查询参数
     * @return {@link List <DeviceLocationResultVO>} 设备位置信息VO列表
     */
    @Override
    public List<DeviceLocationResultVO> getDeviceLocationResultVOList(DeviceLocationPageQuery query) {
        return baseMapper.getDeviceLocationResultVOList(query);
    }

    /**
     *
     * @param deviceLocationUpdateVO 设备位置信息
     */
    private void checkedDeviceLocationUpdateVO(DeviceLocationUpdateVO deviceLocationUpdateVO) {
        ArgumentAssert.notNull(deviceLocationUpdateVO, "deviceLocationSaveVO Cannot be null");
        ArgumentAssert.notNull(deviceLocationUpdateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(deviceLocationUpdateVO.getLatitude(), "latitude Cannot be null");
        ArgumentAssert.notNull(deviceLocationUpdateVO.getLongitude(), "longitude Cannot be null");
        ArgumentAssert.notBlank(deviceLocationUpdateVO.getFullName(), "fullName Cannot be null");
        ArgumentAssert.notBlank(deviceLocationUpdateVO.getProvinceCode(), "provinceCode Cannot be null");
        ArgumentAssert.notBlank(deviceLocationUpdateVO.getCityCode(), "cityCode Cannot be null");
        ArgumentAssert.notBlank(deviceLocationUpdateVO.getRegionCode(), "regionCode Cannot be null");

    }

    private Builder<DeviceLocation> builderDeviceLocationUpdateVO(DeviceLocationUpdateVO deviceLocationUpdateVO) {
        return Builder.of(DeviceLocation::new)
                .with(DeviceLocation::setLatitude, deviceLocationUpdateVO.getLatitude())
                .with(DeviceLocation::setLongitude, deviceLocationUpdateVO.getLongitude())
                .with(DeviceLocation::setFullName, deviceLocationUpdateVO.getFullName())
                .with(DeviceLocation::setProvinceCode, deviceLocationUpdateVO.getProvinceCode())
                .with(DeviceLocation::setCityCode, deviceLocationUpdateVO.getCityCode())
                .with(DeviceLocation::setRegionCode, deviceLocationUpdateVO.getRegionCode())
                .with(DeviceLocation::setRemark, deviceLocationUpdateVO.getRemark())
                .with(DeviceLocation::setCreatedOrgId, deviceLocationUpdateVO.getCreatedOrgId());
    }

    /**
     * 检查 DeviceLocationSaveVO 参数完整性
     *
     * @param deviceLocationSaveVO 要进行检查的对象
     */
    private void checkedDeviceLocationSaveVO(DeviceLocationSaveVO deviceLocationSaveVO) {
        ArgumentAssert.notNull(deviceLocationSaveVO, "deviceLocationSaveVO Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getDeviceIdentification(), "deviceIdentification Cannot be null");
        ArgumentAssert.notNull(deviceLocationSaveVO.getLatitude(), "latitude Cannot be null");
        ArgumentAssert.notNull(deviceLocationSaveVO.getLongitude(), "longitude Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getFullName(), "fullName Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getProvinceCode(), "provinceCode Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getCityCode(), "cityCode Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getRegionCode(), "regionCode Cannot be null");
    }

    /**
     * 构建 DeviceLocationSaveVO 对象
     *
     * @param deviceLocationSaveVO 要进行构建的对象
     * @return 构建好的 DeviceLocation 对象
     */
    private DeviceLocation builderDeviceLocationSaveVO(DeviceLocationSaveVO deviceLocationSaveVO) {
        DeviceLocation deviceLocation = BeanUtil.toBeanIgnoreError(deviceLocationSaveVO, DeviceLocation.class);
        deviceLocation.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return deviceLocation;
    }
}

